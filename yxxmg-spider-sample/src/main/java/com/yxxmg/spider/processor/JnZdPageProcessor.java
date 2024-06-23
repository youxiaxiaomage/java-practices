package com.yxxmg.spider.processor;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/6/12
 */
public class JnZdPageProcessor implements PageProcessor {
    private static final String ZD_URL = "http://www.jiangning.gov.cn/jnqrmzf/?id=xxgk_418";
    private final Site site =
        Site.me().setCycleRetryTimes(5).setRetryTimes(5).setSleepTime(500).setTimeOut(3 * 60 * 1000)
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0")
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3").setCharset("UTF-8");

    @Override
    public void process(Page page) {
        //
        List<String> relativeUrl = page.getHtml().xpath("/html/body/div/div[1]/ul[1]/li/span[1]/a/@href").all();
        System.out.println(relativeUrl);
    }

    public static void main(String[] args) {
        Spider.create(new JiangNingGovPageProcessor()).addUrl(ZD_URL).thread(10).run();
    }

    @Override
    public Site getSite() {
        return site;
    }
}
