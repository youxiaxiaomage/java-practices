package com.yxxmg.gof.behavior.iterator;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/11
 */
public interface Iterator {
    /**
     * 判断是否存在下一个节点数据
     * 
     * @return true：存在；false不存在
     */
    boolean hasNext();

    /**
     * 接下来数据
     * 
     * @return Object
     */
    Object next();
}
