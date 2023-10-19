package com.yxxmg.hbase.toolkit;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/19
 */
public class GenericTypeUtils {
    private static IGenericTypeResolver GENERIC_TYPE_RESOLVER;

    public static Class<?>[] resolveTypeArguments(final Class<?> clazz, final Class<?> genericIfc) {
        if (null == GENERIC_TYPE_RESOLVER) {
            return SpringReflectionHelper.resolveTypeArguments(clazz, genericIfc);
        }
        return GENERIC_TYPE_RESOLVER.resolveTypeArguments(clazz, genericIfc);
    }

    public static void setGenericTypeResolver(IGenericTypeResolver genericTypeResolver) {
        GENERIC_TYPE_RESOLVER = genericTypeResolver;
    }
}
