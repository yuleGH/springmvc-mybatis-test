package com.yule.common;

import java.util.Collection;
import java.util.Map;

/**
 * @author yule
 * @date 2018/9/28 18:05
 */
public class CommonTool {

    public static boolean isNullOrBlock(Object obj){
        if(obj != null && !obj.toString().equals("")){
            if(obj instanceof Object[]){
                Object[] obj1 = (Object[]) obj;
                return 0 == obj1.length;
            }else if(obj instanceof Collection){
                Collection collection = (Collection) obj;
                return 0 == collection.size();
            }else if(obj instanceof Map){
                Map map = (Map) obj;
                return 0 == map.size();
            }else{
                return false;
            }
        }else{
            return true;
        }
    }

    public static boolean isNotNullOrBlock(Object obj){
        return !isNullOrBlock(obj);
    }
}
