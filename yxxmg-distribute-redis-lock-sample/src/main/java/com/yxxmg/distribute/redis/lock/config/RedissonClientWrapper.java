package com.yxxmg.distribute.redis.lock.config;

import org.redisson.api.RedissonClient;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/7/19
 */
public interface RedissonClientWrapper extends RedissonClient {
    @Override
    CloseableRLock getLock(String name);

    @Override
    CloseableRLock getFairLock(String name);
}
