package com.yule.component.dbcomponent.web.ctrl;

import com.yule.component.dbcomponent.entity.UserTables;
import com.yule.component.dbcomponent.service.DbComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    public String index(){
        return "yule/component/dbcomponent/dbComponent";
    }

    /**
     * 查询所有表名
     * @return
     */
    @RequestMapping("/selectUserTablesNameList")
    @ResponseBody
    public List<UserTables> selectUserTablesNameList(){
        List<UserTables> userTablesList = this.dbComponentService.selectUserTablesNameList();
        return userTablesList;
    }
}
