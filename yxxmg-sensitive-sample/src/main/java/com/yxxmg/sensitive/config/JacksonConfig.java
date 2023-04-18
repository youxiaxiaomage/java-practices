package com.yxxmg.sensitive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.yxxmg.enums.TrimStringSerializer;

/**
 * @author : zhaoyan
 * @version : 1.0
 * @description :
 * @since : 2023/4/18
 */
@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        // 创建一个 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        // 创建一个 SimpleModule 对象，用于注册序列化器
        SimpleModule module = new SimpleModule();
        // 将自定义的 TrimStringSerializer 序列化器注册到 SimpleModule 对象中
        module.addSerializer(String.class, new TrimStringSerializer());
        // 将 SimpleModule 对象注册到 ObjectMapper 中
        objectMapper.registerModule(module);
        // 返回 ObjectMapper 对象
        return objectMapper;
    }
}
