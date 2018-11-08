package com.yule.export.excel.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 导出excel
 * Created by yule on 2018/11/8 16:53.
 */
public interface ExportExcelService {
    /**
     * 导出固定数据的excel
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    void exportExcelByFixedData(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
