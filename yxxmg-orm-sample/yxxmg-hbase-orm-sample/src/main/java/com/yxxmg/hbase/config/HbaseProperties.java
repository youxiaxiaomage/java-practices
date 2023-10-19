package com.yxxmg.hbase.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/19
 */
@Data
@ConfigurationProperties(prefix = "yxxmg.hbase")
public class HbaseProperties {
    private String zkQuorum;
    private int zkClientPort = 2181;
}
