package com.yxxmg.gof.test.createPattern.singleton;

import com.yxxmg.gof.createPattern.singleton.Singleton;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

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
