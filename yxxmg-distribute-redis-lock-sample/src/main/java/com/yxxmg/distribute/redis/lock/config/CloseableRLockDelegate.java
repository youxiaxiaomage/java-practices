package com.yxxmg.distribute.redis.lock.config;

import lombok.experimental.Delegate;
import org.redisson.api.RLock;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/7/19
 */
public class CloseableRLockDelegate implements CloseableRLock {
    @Delegate
    private final RLock rLock;

    public CloseableRLockDelegate(RLock rLock) {
        this.rLock = rLock;
    }
}
