package com.yxxmg.gof.behavior.command;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 电灯对象具备开、关功能
 * @since : 2024/6/5
 */
public class Light {
    public void turnOn() {
        System.out.println("Light is on");
    }

    public void turnOff() {
        System.out.println("Light is off");
    }
}
