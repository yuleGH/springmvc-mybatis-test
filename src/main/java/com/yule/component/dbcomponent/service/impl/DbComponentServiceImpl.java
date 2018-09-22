package com.yule.component.dbcomponent.service.impl;

import com.yule.component.dbcomponent.dao.UserColCommentsDao;
import com.yule.component.dbcomponent.dao.UserTablesDao;
import com.yule.component.dbcomponent.entity.UserColComments;
import com.yule.component.dbcomponent.entity.UserTables;
import com.yule.component.dbcomponent.service.DbComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yule
 * @date 2018/9/22 15:57
 */
public class DbComponentServiceImpl implements DbComponentService {
    @Autowired
    private UserTablesDao userTablesDao;
    @Autowired
    private UserColCommentsDao userColCommentsDao;

    @Transactional
    @Override
    public List<UserTables> selectUserTablesNameList() {
        return this.userTablesDao.selectUserTablesNameList();
    }

    @Override
    public List<UserColComments> selectUserColCommentsListByTbName(String tableName){
        return this.userColCommentsDao.selectUserColCommentsListByTbName(tableName);
    }

}
