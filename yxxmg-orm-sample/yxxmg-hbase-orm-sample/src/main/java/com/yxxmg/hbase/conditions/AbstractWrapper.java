package com.yxxmg.hbase.conditions;

import com.yxxmg.hbase.conditions.interfaces.Compare;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/19
 */
public abstract class AbstractWrapper<T, R, Children extends AbstractWrapper<T, R, Children>> extends Wrapper<T>
    implements Compare<Children, R> {

}
