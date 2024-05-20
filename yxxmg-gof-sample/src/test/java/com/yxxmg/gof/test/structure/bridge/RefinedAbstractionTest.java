package com.yxxmg.gof.test.structure.bridge;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.yxxmg.gof.structure.bridge.Abstraction;
import com.yxxmg.gof.structure.bridge.ConcreteImplementor1;
import com.yxxmg.gof.structure.bridge.Implementor;
import com.yxxmg.gof.structure.bridge.RefinedAbstraction;

@RunWith(JUnit4.class)
public class RefinedAbstractionTest {
    @Test
    public void test() {
        // 定义一个实现化角色
        Implementor imp = new ConcreteImplementor1();
        // 定义一个抽象化角色
        Abstraction abs = new RefinedAbstraction(imp);
        // 执行行文
        abs.request();
    }
}