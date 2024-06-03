package com.yxxmg.gof.test.behavior.state;

import org.junit.Test;

import com.yxxmg.gof.behavior.state.VendingMachine;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/6/3
 */
public class StateTest {
    @Test
    public void test() {
        VendingMachine machine = new VendingMachine();
        machine.insertCoin(); // 输出 "Coin inserted, new state is HasMoney"
        machine.turnCrank(); // 根据HasMoneyState的行为执行
    }
}
