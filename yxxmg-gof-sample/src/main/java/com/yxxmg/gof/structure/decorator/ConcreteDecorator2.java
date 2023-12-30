package com.yxxmg.gof.structure.decorator;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/30
 */
public class ConcreteDecorator2 extends Decorator {
    public ConcreteDecorator2(Component component) {
        super(component);
    }

    private void method2() {
        System.out.println("method2修饰");
    }

    @Override
    public void operate() {
        this.method2();
        super.operate();
    }
}
