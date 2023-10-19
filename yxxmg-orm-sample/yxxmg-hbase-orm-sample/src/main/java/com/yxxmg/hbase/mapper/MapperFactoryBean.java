package com.yxxmg.hbase.mapper;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 工厂bean
 * @since : 2023/10/19
 */
public class MapperFactoryBean<T> implements FactoryBean<T> {
    private Class<T> mapperInterface;

    private boolean addToConfig = true;

    public MapperFactoryBean() {
        // intentionally empty
    }

    public MapperFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public T getObject() throws Exception {
        // 如何实现
        // xxx.getMapper(this.mapperInterface);
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return this.mapperInterface;
    }

    public Class<T> getMapperInterface() {
        return mapperInterface;
    }

    public void setMapperInterface(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public boolean isAddToConfig() {
        return addToConfig;
    }

    public void setAddToConfig(boolean addToConfig) {
        this.addToConfig = addToConfig;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
