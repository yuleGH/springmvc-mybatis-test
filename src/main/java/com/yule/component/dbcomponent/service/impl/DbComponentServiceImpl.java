package com.yule.component.dbcomponent.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yule.component.dbcomponent.dao.UserColCommentsDao;
import com.yule.component.dbcomponent.dao.UserTablesDao;
import com.yule.component.dbcomponent.entity.UserColComments;
import com.yule.component.dbcomponent.entity.UserTables;
import com.yule.component.dbcomponent.service.DbComponentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yule
 * @date 2018/9/22 15:57
 */
public class DbComponentServiceImpl implements DbComponentService {
    @Autowired
    private UserTablesDao userTablesDao;
    @Autowired
    private UserColCommentsDao userColCommentsDao;

    private Logger logger = LoggerFactory.getLogger(DbComponentServiceImpl.class);

    @Override
    public List<UserTables> selectUserTablesList() {
        return this.userTablesDao.selectUserTablesList();
    }

    @Override
    public List<UserTables> selectUserTablesListByTbName(String tableName) {
        return this.userTablesDao.selectUserTablesListByTbName(tableName);
    }

    @Override
    public List<UserColComments> selectUserColCommentsListByTbName(String tableName){
        return this.userColCommentsDao.selectUserColCommentsListByTbName(tableName);
    }

    @Override
    public List<Map<String, String>> getTableData(String tableName, String tableConditionsJson) {
        if(StringUtils.isEmpty(tableName)){
            return new ArrayList<>(0);
        }
        //防止 sql 注入，校验表名
        List<UserTables> userTableList = this.userTablesDao.selectUserTablesListByTbName(tableName);
        if(userTableList == null || userTableList.size() == 0){
            logger.error("表名有问题！");
            return new ArrayList<>(0);
        }

        List<UserColComments> colConditionList = null;
        if(!StringUtils.isEmpty(tableConditionsJson)){
            Gson gson = new Gson();
            colConditionList = gson.fromJson(tableConditionsJson, new TypeToken<List<UserColComments>>(){}.getType());

            //防止 sql 注入，校验列名
            List<UserColComments> userColCommentsList = this.userColCommentsDao.selectUserColCommentsListByColumns(tableName, colConditionList);
            if(userColCommentsList.size() != colConditionList.size()){
                logger.error("查询条件列名有问题！");
                return new ArrayList<>(0);
            }
        }

        //查询表格数据
        return this.userTablesDao.selectTableDataByConditions(tableName, colConditionList);
    }

}
