<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>数据库单表查询组件</title>

    <link rel="stylesheet" href="${ctx}/static/lib/elementui/theme-chalk/index.css" type="text/css">
</head>
<body>
    <div id="app" v-cloak>
        <el-row :gutter="20">
            <el-col :span="6">
                <el-select v-model="tableName" clearable filterable placeholder="请选择">
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
        </el-row>
    </div>

    <!-- 引入组件库 -->
    <script type="text/javascript" src="${ctx}/static/lib/vue.js"></script>
    <script type="text/javascript" src="${ctx}/static/lib/elementui/index.js"></script>
    <script type="text/javascript" src="${ctx}/static/lib/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/static/common/showMsgTool.js"></script>
    <script type="text/javascript" src="${ctx}/static/common/common.js"></script>

    <script type="text/javascript">
        const ctx = '${ctx}';
        new Vue({
            el: "#app",
            data: {
                tableNameOptions: [],
                tableName: ''
            },
            mounted() {
                const _self = this;
                this.$nextTick(() => {
                    commonFun.ajaxSubmit({
                        url: ctx + "/dbComponentCtrl/selectUserTablesListByTbName",
                        datas: {
                            tableName : _self.tableName
                        },
                        backFunction : function(tableNameList){
                            _self.tableNameOptions = tableNameList;
                        }
                    });
                });
            },
            components : {
            },
            methods: {

            }
        });
    </script>

</body>
</html>