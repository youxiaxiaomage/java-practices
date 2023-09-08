package com.yxxmg.leaf.generator;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/9/7
 */
public interface IdGenerator {
    /**
     * 获取数值型分布式ID
     * 
     * @return 生成后的id
     */
    long nextLongId();

    /**
     * 获取字符型分布式ID
     * 
     * @return 生成后的id
     */
    String nextStringId();
}
