package com.yxxmg.gof.structure.decorator;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/30
 */
public class ConcreteDecorator1 extends Decorator {
    public ConcreteDecorator1(Component component) {
        super(component);
    }

    public void method1() {
        System.out.println("method1修饰");
    }

    // 重写父类operate方法
    @Override
    public void operate() {
        this.method1();
        super.operate();
    }
}
