package com.yxxmg.gof.test.createPattern.builder;

import com.yxxmg.gof.createPattern.builder.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 构造器模式测试用例
 * @since : 2023/5/5
 */
@RunWith(JUnit4.class)
public class BuilderTest {
    @Test
    public void test() {
        Client client = Client.builder().clientId("123").clientName("client").requestTimeout(1000L).socketTimeout(2000L)
            .responseTimeout(3000L).build();
        System.out.println(client);
    }
}