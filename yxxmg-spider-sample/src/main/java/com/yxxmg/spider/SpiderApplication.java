package com.yxxmg.spider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/6/12
 */
@SpringBootApplication
@MapperScan("com.yxxmg.spider.mapper")
public class SpiderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpiderApplication.class, args);
    }
}
