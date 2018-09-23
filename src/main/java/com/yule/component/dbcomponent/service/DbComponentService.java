package com.yule.component.dbcomponent.service;

import com.yule.component.dbcomponent.entity.UserColComments;
import com.yule.component.dbcomponent.entity.UserTables;

import java.util.List;
import java.util.Map;

/**
 * @author yule
 * @date 2018/9/22 15:57
 */
public interface DbComponentService {
    /**
     * 查询所有的表名
     */
    List<UserTables> selectUserTablesList();

    /**
     * 查询所有的表名
     * @param tableName
     * @return
     */
    List<UserTables> selectUserTablesListByTbName(String tableName);

    /**
     * 查询某张表的所有字段
     * @param tableName
     * @return
     */
    List<UserColComments> selectUserColCommentsListByTbName(String tableName);

    /**
     * 根据表名获取表格数据
     * @param tableName
     * @param tableConditionsJson
     * @return
     */
    List<Map<String, String>> getTableData(String tableName, String tableConditionsJson);
}
