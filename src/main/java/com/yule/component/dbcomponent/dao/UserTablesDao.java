package com.yule.component.dbcomponent.dao;

import com.yule.component.dbcomponent.entity.UserColComments;
import com.yule.component.dbcomponent.entity.UserTables;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author yule
 * @date 2018/9/22 15:24
 */
public interface UserTablesDao {
    /**selectUserColCommentsListByTbName
     * 查询所有表名
     * @return
     */
    List<UserTables> selectUserTablesList();

    /**
     * 查询表名
     * @param tableName
     * @return
     */
    List<UserTables> selectUserTablesListByTbName(@Param("tableName") String tableName);

    /**
     * 根据表名+查询条件 查询数据
     * @param tableName
     * @param colConditionList
     * @return
     */
    List<Map<String, String>> selectTableDataByConditions(@Param("tableName") String tableName, @Param("colConditionList") List<UserColComments> colConditionList);
}
