package com.yxxmg.jetcache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alicp.jetcache.anno.config.EnableMethodCache;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/3/28
 */
@MapperScan("com.yxxmg.jetcache.mapper")
@EnableMethodCache(basePackages = "com.yxxmg.jetcache")
@SpringBootApplication
public class JetCacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(JetCacheApplication.class, args);
    }
}
