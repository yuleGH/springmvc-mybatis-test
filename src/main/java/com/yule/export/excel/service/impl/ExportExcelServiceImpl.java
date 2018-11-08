package com.yule.export.excel.service.impl;

import com.yule.common.utils.excel.ExportExcelUtil;
import com.yule.export.excel.service.ExportExcelService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 导出excel
 * @author yule
 * @date 2018/11/8 16:53
 */
public class ExportExcelServiceImpl implements ExportExcelService {
    @Override
    public void exportExcelByFixedData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Map<String, String>> dataList = new ArrayList<>();
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("filed1", "字段一");
        dataMap.put("filed2", "字段二");
        dataList.add(dataMap);
        ExportExcelUtil.exportExcel(request, response, "test",
                new String[]{"标题一", "标题二"},
                new String[]{"filed1", "filed2"},
                dataList);
    }
}
