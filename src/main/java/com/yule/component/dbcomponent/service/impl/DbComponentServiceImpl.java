package com.yule.component.dbcomponent.service.impl;

import com.yule.component.dbcomponent.dao.UserColCommentsDao;
import com.yule.component.dbcomponent.dao.UserTablesDao;
import com.yule.component.dbcomponent.entity.UserTables;
import com.yule.component.dbcomponent.service.DbComponentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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

    private final Logger logger = LoggerFactory.getLogger(DbComponentServiceImpl.class);

    @Override
    public List<UserTables> selectUserTablesNameList() {
        logger.info("测试日志哦~");
        return this.userTablesDao.selectUserTablesNameList();
    }

}
