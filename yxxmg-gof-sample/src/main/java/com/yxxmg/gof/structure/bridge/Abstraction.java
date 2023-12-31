package com.yxxmg.gof.structure.bridge;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/30
 */
public abstract class Abstraction {
    // 定义对实现化角色的引用
    private Implementor implementor;

    // 约束子类必须实现该构造函数
    public Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }

    // 自身的行为和属性
    public void request() {
        this.implementor.doSomething();
    }

    public Implementor getImplementor() {
        return implementor;
    }
}
