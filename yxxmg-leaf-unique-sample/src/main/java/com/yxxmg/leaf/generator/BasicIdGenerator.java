package com.yxxmg.leaf.generator;

import cn.hutool.core.lang.Snowflake;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/9/8
 */
public class BasicIdGenerator implements IdGenerator {

    private final Snowflake snowflake;

    /**
     * 构造函数。
     *
     * @param workNode 工作节点。
     */
    public BasicIdGenerator(Integer workNode) {
        snowflake = new Snowflake(workNode, 0);
    }

    /**
     * 获取基于Snowflake算法的数值型Id。 由于底层实现为synchronized方法，因此计算过程串行化，且线程安全。
     *
     * @return 计算后的全局唯一Id。
     */
    @Override
    public long nextLongId() {
        return this.snowflake.nextId();
    }

    /**
     * 获取基于Snowflake算法的字符串Id。 由于底层实现为synchronized方法，因此计算过程串行化，且线程安全。
     *
     * @return 计算后的全局唯一Id。
     */
    @Override
    public String nextStringId() {
        return this.snowflake.nextIdStr();
    }
}
