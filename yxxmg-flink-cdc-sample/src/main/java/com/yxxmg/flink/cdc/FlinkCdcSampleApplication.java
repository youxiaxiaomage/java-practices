package com.yxxmg.flink.cdc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :启动类
 * @since : 2024/8/13
 */
@SpringBootApplication
public class FlinkCdcSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlinkCdcSampleApplication.class, args);
    }
}
