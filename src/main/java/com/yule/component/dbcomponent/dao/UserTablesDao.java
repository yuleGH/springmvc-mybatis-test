package com.yule.component.dbcomponent.dao;

import com.yule.component.dbcomponent.entity.UserTables;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yule
 * @date 2018/9/22 15:24
 */
public interface UserTablesDao {
    /**
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
}
