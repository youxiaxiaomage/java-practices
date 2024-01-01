package com.yxxmg.gof.structure.flyweight;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/1/1
 */
public class ConcreteFlyweight2 extends Flyweight {
    public ConcreteFlyweight2(String _Extrinsic) {
        super(_Extrinsic);
    }

    @Override
    public void operate() {
        System.out.println("ConcreteFlyweight2.operate");
    }
}
