package com.yxxmg.sensitive;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

/**
 * @author : zhaoyan
 * @version : 1.0
 * @description :
 * @since : 2023/4/12
 */
@RunWith(JUnit4.class)
public class SensitiveTest {
    @Test
    public void test() throws JsonProcessingException {
        Student student1 = new Student().setPhoneNumber("15371002835").setEmail("1187961583@qq.com").setUserId("1");
        JsonMapper jsonMapper = new JsonMapper();
        String str = jsonMapper.writeValueAsString(student1);
        System.out.println(str);
    }
}
