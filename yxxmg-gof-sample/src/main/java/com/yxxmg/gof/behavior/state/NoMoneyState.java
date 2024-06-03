package com.yxxmg.gof.behavior.state;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/6/3
 */
public class NoMoneyState implements VendingMachineState {
    private VendingMachine vendingMachine;

    public NoMoneyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Coin inserted, new state is HasMoney");
        vendingMachine.setState(vendingMachine.getHasMoneyState());
    }

    @Override
    public void ejectCoin() {}

    @Override
    public void turnCrank() {}

    @Override
    public void dispense() {}
}
