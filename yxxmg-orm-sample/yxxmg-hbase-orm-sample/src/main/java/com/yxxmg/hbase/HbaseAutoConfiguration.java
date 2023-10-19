package com.yxxmg.hbase;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.yxxmg.hbase.config.HbaseProperties;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/19
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(HbaseProperties.class)
public class HbaseAutoConfiguration {}
