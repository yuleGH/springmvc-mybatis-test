package com.yule.component.dbcomponent.web.ctrl;

import com.yule.component.dbcomponent.entity.UserTables;
import com.yule.component.dbcomponent.service.DbComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
}
