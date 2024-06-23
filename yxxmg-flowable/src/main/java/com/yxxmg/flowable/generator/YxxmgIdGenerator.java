package com.yxxmg.flowable.generator;

import org.flowable.common.engine.impl.cfg.IdGenerator;

import cn.hutool.core.lang.Snowflake;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : id生成器
 * @since : 2024/6/23
 */
public class YxxmgIdGenerator implements IdGenerator {
    @Override
    public String getNextId() {
        return new Snowflake().nextIdStr();
    }
}
