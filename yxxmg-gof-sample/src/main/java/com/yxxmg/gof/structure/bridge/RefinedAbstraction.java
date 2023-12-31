package com.yxxmg.gof.structure.bridge;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/30
 */
public class RefinedAbstraction extends Abstraction {
    public RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }

    @Override
    public void request() {
        super.request();
        super.getImplementor().doAnything();
    }
}
