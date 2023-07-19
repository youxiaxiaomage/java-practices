package com.yxxmg.spring.factory;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/12
 */
public interface Iterable<T> {
    /**
     * 迭代器
     * 
     * @return Iterator
     */
    Iterator<T> iterator();

    /**
     * for each
     * 
     * @param action consumer
     */
    // default void forEach(Consumer<? super T> action) {
    // Objects.requireNonNull(action);
    // for (T t : this) {
    // action.accept(t);
    // }
    // }

}
