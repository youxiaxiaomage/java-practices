package com.yxxmg.gof.behavior.state;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 状态接口
 * @since : 2024/6/3
 */
public interface VendingMachineState {
    void insertCoin();

    void ejectCoin();

    void turnCrank();

    void dispense();
}
