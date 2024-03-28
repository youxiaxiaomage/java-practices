package com.yxxmg.gof.behavior;

import java.util.Observable;
import java.util.Observer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class BankAccountTest {
    @Test
    public void test() {
        BankAccount bankAccount = new BankAccount(1000);
        Observer observer1 = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                System.out.println("客户1：余额已更新为：" + ((BankAccount)o).getBalance());
            }
        };
        Observer observer2 = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                System.out.println("客户2：余额已更新为：" + ((BankAccount)o).getBalance());
            }
        };
        bankAccount.addObserver(observer1);
        bankAccount.addObserver(observer2);
        bankAccount.deposit(100.0);
        bankAccount.withdraw(50.0);
    }
}