package com.yule.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.*;

/**
 * 配置文件工具类
 * @author yule
 * @date 2018/9/28 16:26
 */
public class PropertiesUtils {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

    /**
     * 读取json文件，返回json字符串
     * @param fileName
     * @return
     */
    public static String readJsonFile(String fileName) {
        FileReader fileReader = null;
        Reader reader = null;
        try {
            File jsonFile = ResourceUtils.getFile("classpath:"+fileName);
            fileReader = new FileReader(jsonFile);
            reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            String jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("读取文件报错", e);
        } finally {
            if(fileReader != null){
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
