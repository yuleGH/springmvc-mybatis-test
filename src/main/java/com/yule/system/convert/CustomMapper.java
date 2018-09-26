package com.yule.system.convert;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author yule
 * @date 2018/9/26 14:17
 */
public class CustomMapper extends ObjectMapper {
    public CustomMapper() {
//        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 设置 SerializationFeature.FAIL_ON_EMPTY_BEANS 为 false
//        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }
}
