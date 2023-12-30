package com.yxxmg.gof.structure.decorator;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/30
 */
public abstract class Decorator implements Component {
    private Component component;

    // 通过构造函数传递被修饰者
    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operate() {
        this.component.operate();
    }
}
