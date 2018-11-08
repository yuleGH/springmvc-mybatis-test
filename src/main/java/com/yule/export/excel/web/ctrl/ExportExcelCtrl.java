package com.yule.export.excel.web.ctrl;

import com.yule.export.excel.service.ExportExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 导出excel
 * @author yule
 * @date 2018/11/8 16:50
 */
@Controller
@RequestMapping("/exportExcelCtrl")
public class ExportExcelCtrl {
    @Autowired
    private ExportExcelService exportExcelService;

    /**
     * 导出固定数据的excel
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @RequestMapping("/exportExcelByFixedData")
    public void exportExcelByFixedData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.exportExcelService.exportExcelByFixedData(request, response);
    }
}
