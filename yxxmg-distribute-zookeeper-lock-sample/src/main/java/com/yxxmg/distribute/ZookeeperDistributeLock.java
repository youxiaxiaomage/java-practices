package com.yxxmg.distribute;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;

import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/9/24
 */
@Data
public class ZookeeperDistributeLock implements AutoCloseable {
    private String connectString;
    private static final int SESSION_TIMEOUT = 3000;
    private static final int CONNECTION_TIMEOUT = 3000;
    private static final String CURATOR_LOCK = "/curatorLock";
    private long lockTime;
    private InterProcessMutex interProcessMutex;
    private static CuratorFramework client;
    private volatile static boolean initFlag = Boolean.FALSE;
    private String lockPath;
    static {
        init();
    }

    public ZookeeperDistributeLock(String lockPath) {
        interProcessMutex = new InterProcessMutex(client, lockPath);
    }

    public static void init() {
        if (!initFlag) {
            System.out.println("Client未初始化");
            String s = null;
            // try {
            client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181").sessionTimeoutMs(SESSION_TIMEOUT)
                .connectionTimeoutMs(CONNECTION_TIMEOUT).retryPolicy(new RetryNTimes(3, 5000)).build();
            client.start();
            initFlag = Boolean.TRUE;
        } else {
            System.out.println("已初始化好client");
        }
    }

    public boolean tryLock() throws Exception {
        return interProcessMutex.acquire(lockTime, TimeUnit.MILLISECONDS);
    }

    @Override
    public void close() throws Exception {
        if (interProcessMutex.isAcquiredInThisProcess() && interProcessMutex.isOwnedByCurrentThread()) {
            interProcessMutex.release();
            System.out.println("lock release lock [" + lockPath + "]");
        }
    }
}
