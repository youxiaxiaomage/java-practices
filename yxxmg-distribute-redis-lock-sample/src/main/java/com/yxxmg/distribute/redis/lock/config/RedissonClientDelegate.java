package com.yxxmg.distribute.redis.lock.config;

import lombok.experimental.Delegate;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/7/19
 */
@Component
public class RedissonClientDelegate implements RedissonClientWrapper {
    @Delegate(excludes = ExcludeMethodClass.class)
    private final RedissonClient redissonClient;

    public RedissonClientDelegate(@Autowired RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public CloseableRLock getLock(String name) {
        RLock lock = this.redissonClient.getLock(name);
        return lock != null ? new CloseableRLockDelegate(lock) : null;
    }

    @Override
    public CloseableRLock getFairLock(String name) {
        RLock lock = this.redissonClient.getFairLock(name);
        return lock != null ? new CloseableRLockDelegate(lock) : null;
    }

    private abstract static class ExcludeMethodClass {
        public abstract CloseableRLock getLock(String name);

        public abstract CloseableRLock getFairLock(String name);
    }
}
