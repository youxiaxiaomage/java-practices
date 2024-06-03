package com.yxxmg.gof.behavior.state;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/6/3
 */
public class HasMoneyState implements VendingMachineState {
    private VendingMachine vendingMachine;

    public HasMoneyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Machine is already in HasMoney state, coin will be added to credit");
    }

    @Override
    public void ejectCoin() {

    }

    @Override
    public void turnCrank() {
        System.out.println("Machine is already in HasMoney state, turnCrank is not allowed");
    }

    @Override
    public void dispense() {

    }
}
