package com.yxxmg.okttp.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.yxxmg.okttp.HttpClientUtils;

import cn.hutool.core.lang.Assert;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :这边使用yxxmg-sa-token-sample模块代码做测试
 * @since : 2023/8/5
 */
@RunWith(JUnit4.class)
public class HttpClientUtilsTest {

    @Test
    public void testPost() {

    }

    @Test
    public void testGet() {
        String url = "http://localhost:8081/user/doLogin?username=zhang&password=123456";
        String response = HttpClientUtils.get(url);
        System.out.println(response);
        Assert.isTrue(StringUtils.isNotBlank(response));
    }

    @Test
    public void testPut() {

    }

    @Test
    public void testDelete() {

    }
}