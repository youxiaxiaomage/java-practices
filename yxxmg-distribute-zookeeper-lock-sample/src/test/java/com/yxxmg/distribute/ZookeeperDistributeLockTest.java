package com.yxxmg.distribute;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ZookeeperDistributeLockTest {
    ExecutorService executorService = new ThreadPoolExecutor(5, 10, 5000L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<>(), new BasicThreadFactory.Builder().namingPattern("zk-thread-%d").build(),
        new ThreadPoolExecutor.CallerRunsPolicy());

    @Ignore("xxx")
    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                try (ZookeeperDistributeLock distributeLock = new ZookeeperDistributeLock("/zkLock/test123")) {
                    boolean locked = distributeLock.tryLock();
                    if (locked) {
                        System.out.println("locked success");
                    } else {
                        System.out.println("locked failed");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            });
        }
    }
}