package com.yxxmg.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 启动类
 * @since : 2023/6/6
 */
@Async
@SpringBootApplication
public class EventSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventSampleApplication.class, args);
    }
}
