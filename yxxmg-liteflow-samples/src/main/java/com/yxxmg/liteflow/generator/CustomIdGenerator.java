package com.yxxmg.liteflow.generator;

import com.yomahub.liteflow.flow.id.RequestIdGenerator;

import cn.hutool.core.lang.generator.SnowflakeGenerator;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/4/20
 */
public class CustomIdGenerator implements RequestIdGenerator {
    @Override
    public String generate() {
        return String.valueOf(new SnowflakeGenerator().next());
    }
}
