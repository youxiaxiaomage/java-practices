package com.yxxmg.hbase.toolkit;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/19
 */
public interface IGenericTypeResolver {
    Class<?>[] resolveTypeArguments(final Class<?> clazz, final Class<?> genericIfc);
}
