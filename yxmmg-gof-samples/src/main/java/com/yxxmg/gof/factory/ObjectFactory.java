package com.yxxmg.gof.factory;

import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;

import java.util.Map;
import java.util.Properties;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 对象工厂
 * @since : 2023/5/5
 */
public class ObjectFactory<T> {
    private final static Map<String, IDao<?>> DAO_MAP = Maps.newHashMap();
    private static final String DEFAULT_PROPERTIES_FILE_NAME = "beans.properties";

    static {
        // 加载配置文件
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(DEFAULT_PROPERTIES_FILE_NAME));
            if (CollectionUtils.isEmpty(properties.keySet())) {
                throw new RuntimeException("properties is empty");
            }
            for (Object key : properties.keySet()) {
                Object o = properties.get(key.toString());
                Class<?> clazz = Class.forName(o.toString());
                DAO_MAP.put(key.toString(), (IDao<?>) clazz.newInstance());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static IDao<?> getDaoByName(String name) {
        return DAO_MAP.getOrDefault(name, null);
    }
}
