package com.yxxmg.okttp.test;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.common.collect.Maps;
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
        String request = "{\"test\":\"test\"}";
        String url = "http://localhost:8081/user/test";
        String response = HttpClientUtils.postJson(url, request);
        System.out.println(response);
        Assert.isTrue(StringUtils.isNotBlank(response));
    }

    @Test
    public void testGet() {
        String url = "https://btripopen.alibtrip.com/api/train/v1/bill-settlement?"
            + "app_key=jiiuchg03800&so_corp_token=DgLNQAwI00&period_end=202308-06"
            + "&period_start=2023-01-01&page_no=1&page_size=20";
        String response = HttpClientUtils.get(url);
        System.out.println(response);
        Assert.isTrue(StringUtils.isNotBlank(response));
    }

    @Test
    public void testHeader() {
        String url = "http://localhost:8081/user/testHeader";
        Map<String, String> headers = Maps.newHashMap();
        headers.put("satoken", "123456");
        String response = HttpClientUtils.get(url, headers);
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