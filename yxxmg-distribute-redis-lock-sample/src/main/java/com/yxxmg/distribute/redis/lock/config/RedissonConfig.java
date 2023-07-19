package com.yxxmg.distribute.redis.lock.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/7/19
 */
@Configuration
public class RedissonConfig {
    @Autowired
    private Environment environment;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() throws IOException {
        String[] activeProfiles = this.environment.getActiveProfiles();
        if (activeProfiles.length < 1) {
            throw new IllegalArgumentException("redisson profile does not set, please check redisson_*.yml");
        }
        return Redisson.create(Config
            .fromYAML(new ClassPathResource(String.format("redisson_%s.yml", activeProfiles[0])).getInputStream()));
    }
}
