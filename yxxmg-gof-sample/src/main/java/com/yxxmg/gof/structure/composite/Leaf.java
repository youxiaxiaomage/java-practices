package com.yxxmg.gof.structure.composite;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/1/1
 */
public class Leaf extends Component {
    @Override
    public void doSomething() {
        System.out.println("leaf do something");
    }
}
