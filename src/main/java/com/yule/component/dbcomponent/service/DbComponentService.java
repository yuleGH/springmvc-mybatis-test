package com.yule.component.dbcomponent.service;

import com.yule.component.dbcomponent.entity.UserColComments;
import com.yule.component.dbcomponent.entity.UserTables;

import java.util.List;

/**
 * @author yule
 * @date 2018/9/22 15:57
 */
public interface DbComponentService {
    /**
     * 查询所有的表名
     */
    List<UserTables> selectUserTablesNameList();

    /**
     * 查询某张表的所有字段
     * @param tableName
     * @return
     */
    List<UserColComments> selectUserColCommentsListByTbName(String tableName);
}
