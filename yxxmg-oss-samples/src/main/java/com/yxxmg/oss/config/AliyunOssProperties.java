package com.yxxmg.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/4/21
 */
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
@Data
public class AliyunOssProperties {
    private String endpoint;
    private String accessKeyId;
    private String secretAccessKey;
    private String bucketName;
}
