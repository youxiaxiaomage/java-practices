package com.yxxmg.jetcache.config;

import java.time.Duration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.CacheManager;
import com.alicp.jetcache.template.QuickConfig;
import com.yxxmg.jetcache.entity.User;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/3/28
 */
@Configuration
public class CacheConfig {
    @Resource
    private CacheManager cacheManager;
    private Cache<String, User> userCache;

    @PostConstruct
    public void init() {
        QuickConfig quickConfig =
            QuickConfig.newBuilder("userCache").expire(Duration.ofSeconds(100)).localLimit(50).syncLocal(true).build();
        userCache = this.cacheManager.getOrCreateCache(quickConfig);
    }
}
