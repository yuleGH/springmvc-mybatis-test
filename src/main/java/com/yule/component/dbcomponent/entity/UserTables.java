package com.yule.component.dbcomponent.entity;

/**
 * 用户所有表
 * @author yule
 * @date 2018/9/22 15:30
 */
public class UserTables {
    private String tableName;
    /**
     * 表注释
     */
    private String comments;
    /**
     * 表数据总数
     */
    private String numRows;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getNumRows() {
        return numRows;
    }

    public void setNumRows(String numRows) {
        this.numRows = numRows;
    }
}
