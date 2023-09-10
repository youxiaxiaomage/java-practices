package com.yxxmg.factory;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.yxxmg.exception.BeansException;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/12
 */
public interface ObjectProvider<T> extends ObjectFactory<T>, Iterable<T> {
    /**
     * 获取对象
     * 
     * @param args 参数
     * @return T
     * @throws BeansException bean异常
     */
    T getObject(Object... args) throws BeansException;

    /**
     * 获取对象
     * 
     * @return T
     * @throws BeansException e
     */
    T getIfAvailable() throws BeansException;

    /**
     * 获取对象
     * 
     * @param defaultSupplier Supplier
     * @return T
     * @throws BeansException e
     */
    default T getIfAvailable(Supplier<T> defaultSupplier) throws BeansException {
        T dependency = getIfAvailable();
        return (dependency != null ? dependency : defaultSupplier.get());
    }

    /**
     * 消费对象
     * 
     * @param dependencyConsumer Consumer
     * @throws BeansException e
     */
    default void ifAvailable(Consumer<T> dependencyConsumer) throws BeansException {
        T dependency = getIfAvailable();
        if (dependency != null) {
            dependencyConsumer.accept(dependency);
        }
    }

    /**
     * 获取对象
     * 
     * @return T
     * @throws BeansException e
     */
    T getIfUnique() throws BeansException;

    /**
     * 获取对象
     * 
     * @param defaultSupplier supplier
     * @return T
     * @throws BeansException e
     */
    default T getIfUnqiue(Supplier<T> defaultSupplier) throws BeansException {
        T dependency = getIfUnique();
        return (dependency != null ? dependency : defaultSupplier.get());
    }

    /**
     * 获取Stream
     * 
     * @return Stream
     */
    default Stream<T> stream() {
        throw new UnsupportedOperationException("Multi element access not supported");
    }

    /**
     * 获取Iterator
     * 
     * @return Iterator
     */
    @Override
    default Iterator<T> iterator() {
        return stream().iterator();
    }
}
