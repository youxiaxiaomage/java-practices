package com.yxxmg.oss.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/4/21
 */
@Configuration
@EnableConfigurationProperties(AliyunOssProperties.class)
public class OssAutoConfigure {
}
