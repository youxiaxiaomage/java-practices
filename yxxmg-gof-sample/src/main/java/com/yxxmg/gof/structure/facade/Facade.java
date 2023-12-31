package com.yxxmg.gof.structure.facade;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/31
 */
public class Facade {
    private ClassA classA = new ClassA();
    private ClassB classB = new ClassB();
    private ClassC classC = new ClassC();

    public void methodA() {
        classA.doSomethingA();
    }

    public void methodB() {
        classB.doSomethingB();
    }

    public void methodC() {
        classC.doSomethingC();
    }
}
