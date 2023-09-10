package com.yxxmg.factory;

import com.yxxmg.exception.BeansException;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/12
 */
@FunctionalInterface
public interface ObjectFactory<T> {
    /**
     * 获取对象
     * 
     * @return T
     * @throws BeansException bean异常
     */
    T getObject() throws BeansException;
}
