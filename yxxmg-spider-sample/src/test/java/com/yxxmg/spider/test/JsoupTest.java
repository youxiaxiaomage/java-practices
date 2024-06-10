package com.yxxmg.spider.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/6/10
 */
public class JsoupTest {
    @Test
    public void test() {
        // 用jsoup解析页面
        String html = "<html><head><title>Test</title></head></html>";

        Document doc = Jsoup.parse(html);
        Assert.assertEquals("Test", doc.title());
        Assert.assertEquals("", doc.body().text());
    }
}
