package com.yule.component.dbcomponent.dao;

import com.yule.component.dbcomponent.entity.UserColComments;

import java.util.List;

/**
 * @author yule
 * @date 2018/9/22 15:37
 */
public interface UserColCommentsDao {
    /**
     * 查询某个表下的所有字段
     * @param tableName
     * @return
     */
    List<UserColComments> selectUserColCommentsListByTbName(String tableName);
}
