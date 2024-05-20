package com.yxxmg.gof.test.structure.adapter.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.yxxmg.gof.structure.adapter.spring.*;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/14
 */
@RunWith(JUnit4.class)
public class AdapterTest {
    @Test
    public void test() {
        HandlerA handlerA = new HandlerAImpl();
        HandlerB handlerB = new HandlerBImpl();
        HandlerExecutor handlerExecutor = new HandlerExecutor();
        handlerExecutor.execute(handlerA, "test");
        handlerExecutor.execute(handlerB, 1);
    }
}
