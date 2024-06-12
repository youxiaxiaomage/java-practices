package com.yxxmg.spider.processor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import com.yxxmg.spider.pipeline.DatabasePipeline;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/***
 * 
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/6/10
 */
@Component
public class JiangNingGovPageProcessor implements PageProcessor {
    private static final String GOV_URL = "http://www.jiangning.gov.cn/hdjl/zjdc/myzj";
    private final Site site =
        Site.me().setCycleRetryTimes(5).setRetryTimes(5).setSleepTime(500).setTimeOut(3 * 60 * 1000)
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0")
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3").setCharset("UTF-8");

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        List<String> relativeUrl = html.xpath("/html/body/div[2]/div[2]/ul/li/a/@href").all();
        // 替换+去重
        List<String> relateiveUrlList = relativeUrl.stream().map(s -> StringUtils.replaceFirst(s, ".", GOV_URL))
            .distinct().collect(Collectors.toList());
        page.addTargetRequests(relateiveUrlList);
        String title = page.getHtml().xpath("/html/body/div[2]/div[1]/div/div[1]/text()").get();
        page.putField("title", title);
        String releaseTime = StringUtils
            .replace(page.getHtml().xpath("/html/body/div[2]/div[1]/div/div[2]/span[1]/text()").get(), "发布时间：", "");
        page.putField("releaseTime", releaseTime);
        String content = page.getHtml().xpath("/html/body/div[2]/div[1]/div/div[3]/div[1]/div").get();
        if (StringUtils.isNotBlank(content)) {
            String text = Jsoup.parse(content).text();
            page.putField("content", text);
        }
        if (StringUtils.isBlank(title)) {
            page.setSkip(true);
        }

    }

    @PostConstruct
    public void init() {
        Spider.create(new JiangNingGovPageProcessor())
            .setPipelines(Arrays.asList(new DatabasePipeline(), new FilePipeline("D:\\webmagic\\")))
            .addUrl(GOV_URL + "/index.html", GOV_URL + "/index_1.html", GOV_URL + "/index_2.html",
                GOV_URL + "/index_3.html", GOV_URL + "/index_4.html", GOV_URL + "/index_5.html",
                GOV_URL + "/index_6.html", GOV_URL + "/index_7.html", GOV_URL + "/index_8.html")
            .thread(10).run();
    }

    @Override
    public Site getSite() {
        return site;
    }
}
