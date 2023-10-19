package com.yxxmg.hbase.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yxxmg.hbase.base.BaseMapper;
import com.yxxmg.hbase.service.IService;
import com.yxxmg.hbase.toolkit.ReflectionKit;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/19
 */
public class ServiceImpl<M extends BaseMapper<T>, T> implements IService<T> {
    protected Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected M baseMapper;

    @Override
    public BaseMapper<T> getBaseMapper() {
        return baseMapper;
    }

    @Override
    public Class<T> getEntityClass() {
        return entityCLass;
    }

    protected Class<T> entityCLass = currentModelClass();

    protected Class<T> currentModelClass() {
        return (Class<T>)ReflectionKit.getSuperClassGenericType(this.getClass(), ServiceImpl.class, 0);
    }
}
