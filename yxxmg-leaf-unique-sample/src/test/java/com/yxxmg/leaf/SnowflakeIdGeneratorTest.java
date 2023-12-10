package com.yxxmg.leaf;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.yxxmg.leaf.generator.SnowflakeIdGenerator;

@RunWith(JUnit4.class)
public class SnowflakeIdGeneratorTest {
    @Ignore("xxx")
    @Test
    public void test() throws InterruptedException {
        SnowflakeIdGenerator snowflakeIdGenerator = new SnowflakeIdGenerator("127.0.0.1:2181", 8080, "multiTenant");

        for (int i = 0; i < 1000; i++) {
            System.out.println(snowflakeIdGenerator.nextLongId());
        }
    }

    @Test
    public void test2() {
        System.out.println(new Date(1288834974657L));
    }
}