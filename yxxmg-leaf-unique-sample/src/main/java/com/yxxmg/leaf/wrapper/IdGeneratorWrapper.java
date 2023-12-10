package com.yxxmg.leaf.wrapper;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yxxmg.leaf.config.IdGeneratorProperties;
import com.yxxmg.leaf.generator.BasicIdGenerator;
import com.yxxmg.leaf.generator.IdGenerator;
import com.yxxmg.leaf.generator.SnowflakeIdGenerator;

import cn.hutool.core.util.BooleanUtil;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/9/8
 */
@Component
public class IdGeneratorWrapper {
    @Autowired
    private IdGeneratorProperties properties;
    /**
     * Id生成器接口对象。
     */
    private IdGenerator idGenerator;

    @PostConstruct
    public void init() {
        if (BooleanUtil.isTrue(properties.getAdvanceIdGenerator())) {
            idGenerator =
                new SnowflakeIdGenerator(properties.getZkAddress(), properties.getIdPort(), properties.getZkPath());
        } else {
            idGenerator = new BasicIdGenerator(properties.getSnowflakeWorkNode());
        }
    }

    /**
     * 获取基于Snowflake算法的数值型Id。 由于底层实现为synchronized方法，因此计算过程串行化，且线程安全。
     *
     * @return 计算后的全局唯一Id。
     */
    public long nextLongId() {
        return idGenerator.nextLongId();
    }

    /**
     * 获取基于Snowflake算法的字符串Id。 由于底层实现为synchronized方法，因此计算过程串行化，且线程安全。
     *
     * @return 计算后的全局唯一Id。
     */
    public String nextStringId() {
        return idGenerator.nextStringId();
    }
}
