package com.yxxmg.gof.test.structure.adapter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.yxxmg.gof.structure.adapter.Adapter;
import com.yxxmg.gof.structure.adapter.ConcreteTarget;
import com.yxxmg.gof.structure.adapter.Target;

@RunWith(JUnit4.class)
public class AdapterTest {
    @Test
    public void test() {
        // 原有的业务逻辑
        Target target = new ConcreteTarget();
        target.request();
        // 现在增加了适配器角色后的业务逻辑
        Target target2 = new Adapter();
        target2.request();
    }
}