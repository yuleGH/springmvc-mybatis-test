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
 * @author yule
 * @date 2018/9/22 15:57
 */
@Controller
@RequestMapping("/dbComponentCtrl")
public class DbComponentCtrl {
    @Autowired
    private DbComponentService dbComponentService;

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("yule/component/dbcomponent/dbComponent");
    }

    /**
     * 查询所有表名
     * @return
     */
    @RequestMapping("/selectUserTablesListByTbName")
    @ResponseBody
    public List<UserTables> selectUserTablesListByTbName(String tableName){
        List<UserTables> userTablesList = this.dbComponentService.selectUserTablesListByTbName(tableName);
        return userTablesList;
    }

    /**
     * 查询某表的所有列字段
     * @return
     */
    @RequestMapping("/selectUserColCommentsListByTbName")
    @ResponseBody
    public List<UserColComments> selectUserColCommentsListByTbName(String tableName){
        List<UserColComments> userColCommentsList = this.dbComponentService.selectUserColCommentsListByTbName(tableName);
        return userColCommentsList;
    }

    /**
     * 根据表名获取表格数据
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
