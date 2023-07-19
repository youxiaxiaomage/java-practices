package com.yxxmg.mybatisplussample.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 缓存配置
 * @since : 2022/11/9
 */
@Configuration
public class CacheConfiguration {
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(
            Caffeine.newBuilder().expireAfterWrite(1, TimeUnit.MINUTES).initialCapacity(100).maximumSize(1000));
        return cacheManager;
    }
}
