package com.yxxmg.gof.test.relation.chainReponsiblity;

import com.yxxmg.gof.relation.chainresponsibility.FirstHandler;
import com.yxxmg.gof.relation.chainresponsibility.SecondHandler;
import com.yxxmg.gof.relation.chainresponsibility.ThirdHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 责任链测试用例
 * @since : 2023/5/10
 */
@RunWith(JUnit4.class)
public class ChainTest {
    @Test
    public void test() {
        FirstHandler firstHandler = new FirstHandler();
        SecondHandler secondHandler = new SecondHandler();
        ThirdHandler thirdHandler = new ThirdHandler();
        firstHandler.setNextHandler(secondHandler);
        secondHandler.setNextHandler(thirdHandler);
        firstHandler.handler(15);
    }
}
