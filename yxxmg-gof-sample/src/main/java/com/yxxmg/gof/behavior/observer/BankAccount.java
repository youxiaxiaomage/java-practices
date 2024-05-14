package com.yxxmg.gof.behavior.observer;

import java.util.Observable;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/1/5
 */
public class BankAccount extends Observable {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    // 存款
    public void deposit(double amount) {
        balance += amount;
        setChanged();// 表示状态已发生变化
        notifyObservers();// 通知所有观察者
    }

    // 取款
    public void withdraw(double amount) {
        balance -= amount;
        setChanged();
        notifyObservers();
    }

    public double getBalance() {
        return balance;
    }
}
