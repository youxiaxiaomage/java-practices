package com.yxxmg.hbase.binding;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import com.google.common.collect.Maps;
import com.yxxmg.hbase.config.Configuration;
import com.yxxmg.hbase.exception.BindingException;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/20
 */
public class MapperRegistry {
    private final Configuration configuration;
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = Maps.newHashMap();

    public <T> T getMapper(Class<T> type) {
        MapperProxyFactory<?> mapperProxyFactory = knownMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new BindingException("Type " + type + " is not known to the MapperRegistry.");
        }
        try {
            // TODO
            return mapperProxyFactory.newInstance();
        } catch (Exception e) {
            throw new BindingException("Error getting mapper instance. Cause: " + e, e);
        }
    }

    public Collection<Class<?>> getMappers() {
        return Collections.unmodifiableCollection(knownMappers.keySet());
    }
}
