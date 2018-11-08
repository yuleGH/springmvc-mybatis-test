package com.yule.common.utils.excel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

/**
 * 生成 HSSFCellStyle 样式  + 生成字体
 * @author yule
 * @date 2018/10/5 17:16
 */
public class GenerateCellStyleUtil {

    /**
     * 设置body的样式
     * @param workbook
     * @return
     */
    public static HSSFCellStyle getBodyYellowStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.WHITE.index);
        style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        style2.setBorderBottom(BorderStyle.THIN);
        style2.setBorderLeft(BorderStyle.THIN);
        style2.setBorderRight(BorderStyle.THIN);
        style2.setBorderTop(BorderStyle.THIN);

        style2.setAlignment(HorizontalAlignment.CENTER);
        style2.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFFont font2 = workbook.createFont();
        font2.setBold(false);
        style2.setFont(font2);

        return style2;
    }

    /**
     * 标题的样式
     * @param workbook
     * @return
     */
    public static HSSFCellStyle getHeaderBlueStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        //set the foreground fill color Note: Ensure Foreground color is set prior to background color.
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        //设置为一个填充单元格的前景颜色。
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 设置边框
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        //设置水平居中
        style.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        // 字体加粗
        font.setBold(true);
        style.setFont(font);

        return style;
    }

    /**
     * 设置值和样式，富文本 复合样式（一个单元格多个字体）
     * @param cell 当前单元格
     * @param wholeStr 整个字符串
     * @param strArray 字符串分割的数组
     * @param strFontList 字符串分割后一一对应的字体
     */
    public static void setRichTextCellValue(Cell cell, String wholeStr, String[] strArray, List<Font> strFontList){
        HSSFRichTextString hssfRichTextString = new HSSFRichTextString(wholeStr);
        int strLength = 0;
        for(int i = 0; i < strArray.length; i++){
            hssfRichTextString.applyFont(strLength, strLength + strArray[i].length(), strFontList.get(i));
            strLength = strArray[i].length();
        }
        cell.setCellValue(hssfRichTextString);
    }
}
