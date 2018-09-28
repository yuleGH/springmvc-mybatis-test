<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>数据库单表查询组件</title>

    <link rel="stylesheet" href="${ctx}/static/lib/elementui/theme-chalk/index.css" type="text/css">

    <style type="text/css">
        .m-b20{
            margin-bottom: 20px;
        }
        .m-t20{
            margin-top: 20px;
        }
        .t-center{
            text-align: center;
        }
        .el-row{
             margin-left: 0px !important;
             margin-right: 0px !important;
        }
    </style>

</head>
<body>
<div id="app" v-cloak>
    <el-row :gutter="20">
        <el-col :span="6">
            <el-select v-model="dataSourceSelect" @change="handleDataSourceSelectChange" clearable filterable placeholder="请选择数据源">
                <el-option
                        v-for="item in dataSourceOptions"
                        :key="item"
                        :label="item"
                        :value="item">
                </el-option>
            </el-select>
        </el-col>

        <el-col :span="6">
            <el-select v-model="tableNameSelect" clearable filterable placeholder="请选择表名">
                <el-option
                        v-for="item in tableNameOptions"
                        :key="item.tableName"
                        :label="item.tableName"
                        :value="item.tableName">
                    <span style="float: left">{{ item.tableName }}</span>
                    <span style="float: right; color: #8492a6; font-size: 13px">{{ item.comments }}</span>
                </el-option>
            </el-select>
        </el-col>

        <el-col :span="4">
            <el-button type="primary" @click="handleSelectTable">查询列字段</el-button>
        </el-col>
    </el-row>

    <%--查询条件--%>
    <el-row :gutter="20" class="m-b20">
        <div
                v-for="(item,index) in tableConditions"
                :key="item.columnName"
        >
            <template v-if="judgeIsDateType(item)">
                <el-col :span="8" class="m-t20">
                    <el-date-picker
                            v-model="item.dateValArray"
                            type="datetimerange"
                            :editable="false"
                            format="yyyy-MM-dd HH:mm:ss"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            clearable
                            range-separator="至"
                            :start-placeholder="item.columnName"
                            :end-placeholder="item.columnName">
                    </el-date-picker>
                </el-col>
            </template>
            <template v-else>
                <el-col :span="4" class="m-t20">
                    <el-input v-model="item.columnVal" :placeholder="item.columnName"
                            @keyup.enter.native="handleQueryTableDataByCondition"></el-input>
                </el-col>
            </template>
        </div>
    </el-row>
    <el-row :gutter="20" class="m-b20">
        <el-col>
            <el-button type="primary" @click="handleQueryTableDataByCondition">根据条件查询数据</el-button>
        </el-col>
    </el-row>

    <%--表格数据--%>
    <table-component ref="tableRef" :table-columns="tableColumns"></table-component>
</div>

<div id="tableApp">
    <div>
        <el-table
                class="m-b20"
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
                class="t-center"
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
            tableNameOptions: [], //表下拉框
            tableNameSelect: '', //表下拉框选中数据

            dataSourceOptions: [],//数据源下拉框
            dataSourceSelect: '',//数据源下拉框选中的数据

            tableName: '', //当前查询的表名

            tableColumns: [], //表格列名
            tableConditions: [] //表格查询条件
        },
        mounted() {
            const _self = this;
            this.$nextTick(() => {
                commonFun.ajaxSubmit({
                    url: ctx + "/dbComponentCtrl/getDbComponentDataSources",
                    datas: {},
                    backFunction: function (dataSourceList) {
                        _self.dataSourceOptions = dataSourceList;
                    }
                });

            });
        },
        components: {
            'table-component': tableComponent
        },
        methods: {
            /**
             * 处理选择数据源触发
             */
            handleDataSourceSelectChange(dataSourceSelect) {
                if(!dataSourceSelect) return;

                const _self = this;
                commonFun.ajaxSubmit({
                    url: ctx + "/dbComponentCtrl/selectUserTablesListByTbName",
                    datas: {dataSourceType: dataSourceSelect},
                    backFunction: function (tableNameList) {
                        _self.tableNameSelect = "";
                        _self.tableColumns = [];
                        _self.tableConditions = [];
                        _self.tableNameOptions = tableNameList;
                    }
                });
            },
            /**
             * 选择表名
             */
            handleSelectTable() {
                const _self = this;
                const dataSourceType = this.dataSourceSelect;
                this.tableName = this.tableNameSelect;

                commonFun.ajaxSubmit({
                    url: ctx + "/dbComponentCtrl/selectUserColCommentsListByTbName",
                    datas: {
                        tableName: _self.tableName,
                        dataSourceType: dataSourceType
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
                        columnVal: "",
                        dateValArray: []
                    });
                }
            },
            /**
             * 根据查询条件获取表格数据
             */
            handleQueryTableDataByCondition() {
                const tableConditions = this.tableConditions;
                const _self = this;
                tableConditions.forEach(function(x, i){
                    if(_self.judgeIsDateType(x)){
                        x.columnVal = null;
                        x.startDateVal = (x.dateValArray && x.dateValArray.length > 0) ? x.dateValArray[0] : null;
                        x.endDateVal = (x.dateValArray && x.dateValArray.length > 0) ? x.dateValArray[1] : null;
                    }else{
                        x.dateValArray = null;
                        x.startDateVal = null;
                        x.endDateVal = null;
                    }
                });
                this.commonGetTableData(tableConditions);
            },
            commonGetTableData(tableConditions) {
                const tableName = this.tableName;
                if(!tableName){
                    return;
                }

                const dataSourceType = this.dataSourceSelect;
                this.$refs.tableRef.reload({
                    tableName: tableName,
                    dataSourceType: dataSourceType,
                    tableConditionsJson: tableConditions && JSON.stringify(tableConditions)
                });
            },

            /**
             * 判断字段是否为日期类型
             * @param item
             * @returns {boolean}
             */
            judgeIsDateType(item){
                return item.dataType.indexOf('TIMESTAMP') >= 0 || item.dataType.indexOf('DATE') >= 0;
            }
        }
    });
</script>

</body>
</html>