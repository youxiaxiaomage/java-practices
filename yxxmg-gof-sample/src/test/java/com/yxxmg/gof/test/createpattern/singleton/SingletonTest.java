package com.yxxmg.gof.test.createpattern.singleton;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.yxxmg.gof.create.singleton.Singleton;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 单例模式测试用例
 * @since : 2023/5/5
 */
@RunWith(JUnit4.class)
public class SingletonTest {
    @Test
    public void test() {
        String test = Singleton.getInstance().test();
        Assert.assertEquals("test", test);
    }
}
