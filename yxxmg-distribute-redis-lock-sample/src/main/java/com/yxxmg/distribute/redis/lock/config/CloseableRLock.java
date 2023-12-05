package com.yxxmg.distribute.redis.lock.config;

import java.io.Closeable;

import org.redisson.api.RLock;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/7/19
 */
public interface CloseableRLock extends Closeable, RLock {
    @Override
    default void close() {
        if (isLocked() && isHeldByCurrentThread()) {
            unlock();
        }
    }
}
