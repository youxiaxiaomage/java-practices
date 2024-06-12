package com.yxxmg.spider.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxxmg.spider.entity.SiteEntity;

/***
 * 
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/6/12
 */
public interface SiteService extends IService<SiteEntity> {
    void saveSite(SiteEntity siteEntity);
}
