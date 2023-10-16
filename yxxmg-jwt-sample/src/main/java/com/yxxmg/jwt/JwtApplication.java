package com.yxxmg.jwt;

import com.yxxmg.jwt.config.JwtConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/16
 */
@EnableConfigurationProperties(JwtConfig.class)
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan("com.yxxmg.jwt.mapper")
public class JwtApplication {
    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class, args);
    }
}
