package com.yxxmg.gof.test.structure.decorator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.yxxmg.gof.structure.decorator.Component;
import com.yxxmg.gof.structure.decorator.ConcreteComponent;
import com.yxxmg.gof.structure.decorator.ConcreteDecorator1;
import com.yxxmg.gof.structure.decorator.ConcreteDecorator2;

@RunWith(JUnit4.class)
public class ConcreteDecorator2Test {
    @Test
    public void test() {
        Component component = new ConcreteComponent();
        // 第一次修饰
        component = new ConcreteDecorator1(component);
        // 第二次修饰
        component = new ConcreteDecorator2(component);
        // 修饰后运行
        component.operate();
    }

}