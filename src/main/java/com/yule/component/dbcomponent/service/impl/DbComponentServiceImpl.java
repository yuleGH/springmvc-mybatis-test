package com.yule.component.dbcomponent.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yule.common.CommonTool;
import com.yule.component.dbcomponent.dao.UserColCommentsDao;
import com.yule.component.dbcomponent.dao.UserTablesDao;
import com.yule.component.dbcomponent.entity.UserColComments;
import com.yule.component.dbcomponent.entity.UserTables;
import com.yule.component.dbcomponent.service.DbComponentService;
import com.yule.component.dbcomponent.utils.DbLimitUtil;
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
    public List<UserTables> selectUserTablesListByTbName(String tableName) {
        List<String> tableNameLimitList = DbLimitUtil.getTableNameLimitList();
        return this.userTablesDao.selectUserTablesListByTbName(tableName, tableNameLimitList);
    }

    @Override
    public List<UserColComments> selectUserColCommentsListByTbName(String tableName){
        return getAllTableColCommentsListByDelLimit(tableName);
    }

    @Override
    public List<Map<String, String>> getTableData(String tableName, String tableConditionsJson, Map<String, Object> pageConfMap) {
        if(StringUtils.isEmpty(tableName)){
            return new ArrayList<>(0);
        }
        //防止 sql 注入，校验表名
        List<UserTables> userTableList = this.selectUserTablesListByTbName(tableName);
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
        pageConfMap.put("tableName", tableName);
        pageConfMap.put("colConditionList", colConditionList);
        pageConfMap.put("allTableColCommentsListByDelLimit", getAllTableColCommentsListByDelLimit(tableName));
        return this.userTablesDao.selectTableDataByConditions(pageConfMap);
    }

    /**
     * 获取表里所有的字段，排除不能显示的
     * @param tableName
     * @return
     */
    private List<UserColComments> getAllTableColCommentsListByDelLimit(String tableName) {
        List<UserColComments> allTableColCommentsListByDelLimit = new ArrayList<>();

        List<String> tableColumnLimitList = DbLimitUtil.getTableColumnLimitListByTableName(tableName);
        List<UserColComments> allTableColCommentsList = this.userColCommentsDao.selectUserColCommentsListByTbName(tableName);
        if(CommonTool.isNullOrBlock(tableColumnLimitList)){
            return allTableColCommentsList;
        }

        for(UserColComments userColComments : allTableColCommentsList){
            if(!tableColumnLimitList.contains(userColComments.getColumnName())){
                allTableColCommentsListByDelLimit.add(userColComments);
            }
        }

        return allTableColCommentsListByDelLimit;
    }

    @Override
    public int getTableDataCount(String tableName, String tableConditionsJson) {
        Gson gson = new Gson();
        List<UserColComments> colConditionList = gson.fromJson(tableConditionsJson, new TypeToken<List<UserColComments>>(){}.getType());
        return this.userTablesDao.selectTableDataCountByConditions(tableName, colConditionList);
    }

}
