<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>数据库单表查询组件</title>

    <link rel="stylesheet" href="${ctx}/static/lib/elementui/theme-chalk/index.css" type="text/css">
</head>
<body>
<div id="app" v-cloak>
    <el-row :gutter="20" style="margin-bottom: 20px;">
        <el-col :span="6">
            <el-select v-model="tableNameSelect" clearable filterable placeholder="请选择">
                <el-option
                        v-for="item in tableNameOptions"
                        :key="item.tableName"
                        :label="item.tableName"
                        :value="item.tableName">
                    <span style="float: left">{{ item.tableName }}</span>
                    <span style="float: right; color: #8492a6; font-size: 13px">{{ item.comments }} {{ item.numRows}}条记录</span>
                </el-option>
            </el-select>
        </el-col>
        <el-col :span="4">
            <el-button type="primary" @click="handleSelectTable">查询列字段</el-button>
        </el-col>
    </el-row>

    <%--查询条件--%>
    <el-row :gutter="20" style="margin-bottom: 20px;">
        <el-col
                :span="4"
                v-for="(item,index) in tableConditions"
                :key="item.columnName"
        >
            <el-input v-model="item.columnVal" :placeholder="item.columnName"
                      @keyup.enter.native="handleQueryTableDataByCondition"></el-input>
        </el-col>
        <el-col :span="4">
            <el-button type="primary" @click="handleQueryTableDataByCondition">根据条件查询数据</el-button>
        </el-col>
    </el-row>

    <%--表格数据--%>
    <table-component ref="tableRef" :table-columns="tableColumns"></table-component>
</div>

<div id="tableApp">
    <div>
        <el-table
                :data="pageData"
                border
                v-loading="loading"
                @sort-change="sortChange"
                style="width: 100%">
            <el-table-column
                    v-for="(item, index) in tableColumns"
                    :prop="item.prop"
                    :label="item.label"
                    :key="item.id"
                    sortable="custom"
                    show-overflow-tooltip
            >
            </el-table-column>
        </el-table>

        <!--分页-->
        <el-pagination
                :page-size="pageSize" layout="prev, pager, next, jumper" :total="total"
                :current-page="currentPage" @current-change="currentChange">
        </el-pagination>
    </div>
</div>

<!-- 引入组件库 -->
<script type="text/javascript" src="${ctx}/static/lib/vue.js"></script>
<script type="text/javascript" src="${ctx}/static/lib/elementui/index.js"></script>
<script type="text/javascript" src="${ctx}/static/lib/jquery.js"></script>
<script type="text/javascript" src="${ctx}/static/common/common.js"></script>
<script type="text/javascript" src="${ctx}/static/common/paginationTool.js"></script>

<%--表格组件，需要分页--%>
<script type="text/javascript">
    const tableComponent = {
        template: document.getElementById("tableApp").innerHTML,
        mixins: [commonPageBar],
        data: function () {
            return {
                pageSize: 10,
                url: ctx + "/dbComponentCtrl/getTableData"
            }
        },
        props: {
            tableColumns: {
                type: Array,
                required: true
            }
        },
        methods: {
            clearTable(){
                this.pageData = [];
                this.total = 0;
                this.field = null;
                this.direction = null;
                this.currentPage = 0;
            }
        }
    };
</script>

<script type="text/javascript">
    const ctx = '${ctx}';
    new Vue({
        el: "#app",
        data: {
            tableNameOptions: [], //下拉框
            tableNameSelect: '', //下拉框选中数据

            tableName: '', //当前查询的表名

            tableColumns: [], //表格列名
            tableConditions: [] //表格查询条件
        },
        mounted() {
            const _self = this;
            this.$nextTick(() => {
                commonFun.ajaxSubmit({
                    url: ctx + "/dbComponentCtrl/selectUserTablesListByTbName",
                    datas: {},
                    backFunction: function (tableNameList) {
                        _self.tableNameOptions = tableNameList;
                    }
                });
            });
        },
        components: {
            'table-component': tableComponent
        },
        methods: {
            /**
             * 选择表名
             */
            handleSelectTable() {
                const _self = this;
                this.tableName = this.tableNameSelect;

                commonFun.ajaxSubmit({
                    url: ctx + "/dbComponentCtrl/selectUserColCommentsListByTbName",
                    datas: {
                        tableName: _self.tableName
                    },
                    backFunction: function (tableColList) {
                        _self.drawTable(tableColList);
                        _self.drawTableConditions(tableColList);
                    }
                });
            },
            /**
             * 画表格
             * @param tableColList
             */
            drawTable(tableColList) {
                const _self = this;
                _self.tableColumns = [];
                for (let i = 0; i < tableColList.length; i++) {
                    let item = tableColList[i];
                    _self.tableColumns.push({
                        //{tableName: "T_USER", columnName: "ID", comments: "主键"}
                        id: item.columnName,
                        prop: item.columnName,
                        label: item.columnName + "：" + item.comments
                    });
                    _self.$refs.tableRef.clearTable();
                }
            },
            /**
             * 画表格的查询条件
             * @param tableColList
             */
            drawTableConditions(tableColList) {
                const _self = this;
                _self.tableConditions = [];
                for (let i = 0; i < tableColList.length; i++) {
                    let item = tableColList[i];
                    _self.tableConditions.push({
                        //{tableName: "T_USER", columnName: "ID", comments: "主键"}
                        columnName: item.columnName,
                        dataType: item.dataType,
                        columnVal: ""
                    });
                }
            },
            /**
             * 根据查询条件获取表格数据
             */
            handleQueryTableDataByCondition() {
                const tableConditions = this.tableConditions;
                this.commonGetTableData(tableConditions);
            },
            commonGetTableData(tableConditions) {
                const tableName = this.tableName;
                if(!tableName){
                    return;
                }

                this.$refs.tableRef.reload({
                    tableName: tableName,
                    tableConditionsJson: tableConditions && JSON.stringify(tableConditions)
                });
            }
        }
    });
</script>

</body>
</html>