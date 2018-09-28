package com.yule.component.dbcomponent.web.ctrl;

import com.yule.component.dbcomponent.entity.UserColComments;
import com.yule.component.dbcomponent.entity.UserTables;
import com.yule.component.dbcomponent.service.DbComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库组件
 * 需要解决问题：
 *      私密性：后台需要有个 properties 设置哪些表不能查，哪些表的字段不能查
 *      组件：弄成 jar 包，包含前端
 *      日期查询条件处理：时间类型的直接给时间段，年月日时分
 *      考虑返回 Map 后的字段类型处理
 * @author yule
 * @date 2018/9/22 15:57
 */
@Controller
@RequestMapping("/dbComponentCtrl")
public class DbComponentCtrl {
    @Autowired
    private DbComponentService dbComponentService;

    @RequestMapping("/index")
    public String index(){
        return "yule/component/dbcomponent/dbComponent";
    }

    /**
     * 查询所有表名，有限制私密
     * @return
     */
    @RequestMapping("/selectUserTablesListByTbName")
    @ResponseBody
    public List<UserTables> selectUserTablesListByTbName(String tableName){
        List<UserTables> userTablesList = this.dbComponentService.selectUserTablesListByTbName(tableName);
        return userTablesList;
    }

    /**
     * 查询某表的所有列字段，没有限制私密
     * @return
     */
    @RequestMapping("/selectUserColCommentsListByTbName")
    @ResponseBody
    public List<UserColComments> selectUserColCommentsListByTbName(String tableName){
        List<UserColComments> userColCommentsList = this.dbComponentService.selectUserColCommentsListByTbName(tableName);
        return userColCommentsList;
    }

    /**
     * 根据表名获取表格数据，有限制私密
     * @param tableName 表名
     * @param tableConditionsJson 表的查询条件
     * @return
     */
    @RequestMapping("/getTableData")
    @ResponseBody
    public Object getTableData(String tableName, String tableConditionsJson,
                               int start, int limit, String field, String direction){
        Map<String, Object> pageConfMap = new HashMap<>(16);
        pageConfMap.put("start", start);
        pageConfMap.put("limit", limit);
        pageConfMap.put("field", field);
        pageConfMap.put("direction", direction);
        List<Map<String, String>> tableData = this.dbComponentService.getTableData(tableName, tableConditionsJson, pageConfMap);
        int totalCount = 0;
        if(tableData.size() > 0){
            totalCount = this.dbComponentService.getTableDataCount(tableName, tableConditionsJson);
        }

        Map result = new HashMap<>(2);
        result.put("pageData", tableData);
        result.put("total", totalCount);
        return result;
    }
}
