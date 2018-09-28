package com.yule.component.dbcomponent.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yule.common.CommonTool;
import com.yule.component.dbcomponent.entity.LimitInfo;
import org.springframework.util.StringUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库查询限制
 *
 * @author yule
 * @date 2018/9/28 15:58
 */
public class DbLimitUtil {
    private static String json;
    private static List<LimitInfo> limitInfoList;

    static {
        json = PropertiesUtils.readJsonFile("conf/system/limit.json");
        Gson gson = new Gson();
        limitInfoList = gson.fromJson(json, new TypeToken<List<LimitInfo>>(){}.getType());

        for(LimitInfo limitInfo : limitInfoList){
            if(!StringUtils.isEmpty(limitInfo.getTableName())){
                limitInfo.setTableName(limitInfo.getTableName().toUpperCase());
            }

            if(CommonTool.isNotNullOrBlock(limitInfo.getTableColumns())){
                List<String> newTableColumnList = new ArrayList<>();
                for(String str : limitInfo.getTableColumns()){
                    newTableColumnList.add(StringUtils.isEmpty(str) ? str : str.toUpperCase());
                }
                limitInfo.setTableColumns(newTableColumnList);
            }
        }
    }

    public static void main( String[] args ) throws Exception
    {
        System.out.println(DbLimitUtil.getTableNameLimitList());
        System.out.println(DbLimitUtil.getTableColumnLimitListByTableName("t_user"));
    }

    /**
     * 获取限制整个表的表名List
     * @return
     */
    public static List<String> getTableNameLimitList(){
        List<String> tableNameLimitList = new ArrayList<>();
        for(LimitInfo limitInfo : limitInfoList){
            if(CommonTool.isNullOrBlock(limitInfo.getTableColumns())){
                tableNameLimitList.add(limitInfo.getTableName());
            }
        }
        return tableNameLimitList;
    }

    /**
     * 获取某表下的限制列
     * @param tableName
     * @return
     */
    public static List<String> getTableColumnLimitListByTableName(String tableName){
        List<String> tableColumnLimitList = new ArrayList<>();

        if(StringUtils.isEmpty(tableName)){
            return tableColumnLimitList;
        }

        tableName = tableName.toUpperCase();

        Map<String, List<String>> tableLimitMap = getTableLimitMap();
        if(!tableLimitMap.containsKey(tableName)){
            return tableColumnLimitList;
        }

        return tableLimitMap.get(tableName);

    }

    private static Map<String, List<String>> getTableLimitMap(){
        Map<String, List<String>> tableLimitMap = new HashMap<>();
        for(LimitInfo limitInfo : limitInfoList){
            tableLimitMap.put(limitInfo.getTableName(), limitInfo.getTableColumns());
        }
        return tableLimitMap;
    }
}
