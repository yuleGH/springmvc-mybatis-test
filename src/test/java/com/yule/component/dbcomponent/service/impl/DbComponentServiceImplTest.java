package com.yule.component.dbcomponent.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yule.user.entity.User;
import oracle.sql.TIMESTAMP;
import org.junit.Test;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

public class DbComponentServiceImplTest {

    @Test
    public void test() throws JsonProcessingException {
        User user = new User("1", "32", "sfd");
        user.setUpdateTime(getOracleTimestamp(new TIMESTAMP()));
//        user.setUpdateTime(new TIMESTAMP());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);
    }

    /**
     * @reference oracle.sql.Datum.timestampValue();
     * @return
     */
    private Timestamp getOracleTimestamp(Object value) {
        try {
            Class clz = value.getClass();
            Method m = clz.getMethod("timestampValue");
            //m = clz.getMethod("timeValue", null); 时间类型
            //m = clz.getMethod("dateValue", null); 日期类型
            return (Timestamp) m.invoke(value);

        } catch (Exception e) {
            return null;
        }
    }
}