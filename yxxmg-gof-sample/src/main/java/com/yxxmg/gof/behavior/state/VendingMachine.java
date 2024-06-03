package com.yxxmg.gof.behavior.state;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 上下文自动贩卖机
 * @since : 2024/6/3
 */
public class VendingMachine {
    private VendingMachineState state;

    public VendingMachine() {
        this.state = new NoMoneyState(this);
    }

    public VendingMachineState getState() {
        return state;
    }

    public void setState(VendingMachineState state) {
        this.state = state;
    }

    public void insertCoin() {
        state.insertCoin();
    }

    public void ejectCoin() {
        state.ejectCoin();
    }

    public void turnCrank() {
        state.turnCrank();
    }

    public void dispense() {
        state.dispense();
    }

    public VendingMachineState getHasMoneyState() {
        return new HasMoneyState(this);
    }
}
