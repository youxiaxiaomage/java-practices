package com.yxxmg.juc;

import java.util.concurrent.*;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import lombok.SneakyThrows;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : CompletableFuture
 * @since : 2022/12/5
 */
@RunWith(JUnit4.class)
public class CompletableFutureTest {
    /**
     * 自定义线程池
     */
    private static final Executor THREAD_POOL = new ThreadPoolExecutor(5, 10, 1, TimeUnit.MILLISECONDS,
        new LinkedBlockingDeque<>(), new BasicThreadFactory.Builder().namingPattern("yxxmg-test-%d").build(),
        new ThreadPoolExecutor.CallerRunsPolicy());

    @SneakyThrows
    @Test
    public void testCompletableFuture() {
        for (int i = 0; i < 10; i++) {
            CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(RandomUtils.nextInt(1000, 2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return Thread.currentThread().getName();
            }, THREAD_POOL);
            System.out.println(completableFuture.get());
        }
    }
}
