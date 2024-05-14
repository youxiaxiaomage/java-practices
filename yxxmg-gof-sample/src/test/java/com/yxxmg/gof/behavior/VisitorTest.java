package com.yxxmg.gof.behavior;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.yxxmg.gof.behavior.visitor.*;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/14
 */
@RunWith(JUnit4.class)
public class VisitorTest {
    @Test
    public void test() {
        Visitor visitor = new ConcreteVisitor();
        for (int i = 0; i < 10; i++) {
            Element element = createElement();
            element.accept(visitor);
        }
    }

    private static Element createElement() {
        Random random = new Random();
        if (random.nextInt(100) > 50) {
            return new ConcreteElementA();
        } else {
            return new ConcreteElementB();
        }
    }
}
