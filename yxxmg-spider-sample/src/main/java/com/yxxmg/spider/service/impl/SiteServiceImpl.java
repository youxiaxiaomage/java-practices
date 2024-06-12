package com.yxxmg.spider.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxxmg.spider.entity.SiteEntity;
import com.yxxmg.spider.mapper.SiteMapper;
import com.yxxmg.spider.service.SiteService;

/***
 * 
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/6/12
 */
@Service
public class SiteServiceImpl extends ServiceImpl<SiteMapper, SiteEntity> implements SiteService {
    @Override
    public void saveSite(SiteEntity siteEntity) {
        save(siteEntity);
    }
}
