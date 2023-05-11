package com.yxxmg.function;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/11
 */
@FunctionalInterface
public interface Animal {
    /**
     * eat
     * 
     * @param animal 动物
     * @return 打印
     */
    String eat(String animal);
}
