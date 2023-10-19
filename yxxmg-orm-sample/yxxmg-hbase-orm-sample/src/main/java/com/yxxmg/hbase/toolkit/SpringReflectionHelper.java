package com.yxxmg.hbase.toolkit;

import org.springframework.core.GenericTypeResolver;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/19
 */
public class SpringReflectionHelper {
    public static Class<?>[] resolveTypeArguments(Class<?> clazz, Class<?> genericIfc) {
        return GenericTypeResolver.resolveTypeArguments(clazz, genericIfc);

    }
}
