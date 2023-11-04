package com.yxxmg.easy.trans;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cloud.client.discovery.composite.CompositeDiscoveryClientAutoConfiguration;
import org.springframework.cloud.client.discovery.simple.SimpleDiscoveryClientAutoConfiguration;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/11/2
 */
@SpringBootApplication(exclude = {RedisAutoConfiguration.class, SimpleDiscoveryClientAutoConfiguration.class,
    CompositeDiscoveryClientAutoConfiguration.class})
@MapperScan("com.yxxmg.easy.trans.mapper")
public class EasyTransApplication {
    public static void main(String[] args) {
        SpringApplication.run(EasyTransApplication.class, args);
    }
}
