package com.yxxmg.spider.pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yxxmg.spider.entity.SiteEntity;
import com.yxxmg.spider.service.SiteService;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/***
 * 
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/6/12
 */
@Component
public class DatabasePipeline implements Pipeline {
    private static SiteService siteService;

    @Autowired
    public void setSiteService(SiteService siteService) {
        DatabasePipeline.siteService = siteService;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        String url = resultItems.getRequest().getUrl();
        String title = resultItems.get("title");
        String releaseTime = resultItems.get("releaseTime");
        String content = resultItems.get("content");

        SiteEntity siteEntity =
            SiteEntity.builder().url(url).title(title).content(content).releaseTime(releaseTime).build();
        siteService.saveSite(siteEntity);
    }
}
