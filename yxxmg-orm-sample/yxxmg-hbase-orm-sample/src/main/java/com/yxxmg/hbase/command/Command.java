package com.yxxmg.hbase.command;

import java.lang.reflect.Method;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/19
 */
public interface Command<T> {
    Object invoke(Method method, Object[] args);
}
