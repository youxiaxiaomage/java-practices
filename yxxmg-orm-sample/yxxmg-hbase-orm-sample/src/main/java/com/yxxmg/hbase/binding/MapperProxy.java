package com.yxxmg.hbase.binding;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/19
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {
    private final Class<T> mapperInterface;

    public MapperProxy(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            if (Object.class.equals(method.getDeclaringClass())) {
                return method.invoke(this, args);
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    interface MapperMethodInvoker {
        Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
    }

    private static class PlainMethodInvoker implements MapperMethodInvoker {
        private final MapperMethod mapperMethod;

        PlainMethodInvoker(MapperMethod mapperMethod) {
            super();
            this.mapperMethod = mapperMethod;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return mapperMethod.execute(args);
        }
    }
}
