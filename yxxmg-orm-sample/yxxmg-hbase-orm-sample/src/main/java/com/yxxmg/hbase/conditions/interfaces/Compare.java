package com.yxxmg.hbase.conditions.interfaces;

import java.io.Serializable;
import java.util.Map;
import java.util.function.BiPredicate;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/19
 */
public interface Compare<Children, R> extends Serializable {
    default <V> Children allEq(Map<R, V> params) {
        return allEq(params, true);
    }

    default <V> Children allEq(Map<R, V> params, boolean null2IsNull) {
        return allEq(true, params, null2IsNull);
    }

    <V> Children allEq(boolean b, Map<R, V> params, boolean null2IsNull);

    default <V> Children allEq(BiPredicate<R, V> filter, Map<R, V> params) {
        return allEq(filter, params, true);
    }

    default <V> Children allEq(BiPredicate<R, V> filter, Map<R, V> params, boolean null2IsNull) {
        return allEq(true, filter, params, null2IsNull);
    }

    <V> Children allEq(boolean condition, BiPredicate<R, V> filter, Map<R, V> params, boolean null2IsNull);
}
