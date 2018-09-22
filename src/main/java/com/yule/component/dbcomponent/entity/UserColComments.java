package com.yule.component.dbcomponent.entity;

/**
 * @author yule
 * @date 2018/9/22 15:38
 */
public class UserColComments {
    /**
     * 列名所属表
     */
    private String tableName;
    /**
     * 列字段名
     */
    private String columnName;
    /**
     * 列注释
     */
    private String comments;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
