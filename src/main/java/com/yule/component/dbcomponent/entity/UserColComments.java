package com.yule.component.dbcomponent.entity;

/**
 * 列的一些信息
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

    /**
     * 字段类型
     */
    private String dataType;

    /**
     * 列值，不是数据库的字段
     */
    private String columnVal;

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

    public String getColumnVal() {
        return columnVal;
    }

    public void setColumnVal(String columnVal) {
        this.columnVal = columnVal;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
