package com.yxxmg.gof.createPattern.singleton;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 懒汉模式 饿汉模式
 * @since : 2023/5/5
 */
public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public String test() {
        System.out.println("Singleton.test");
        return "test";
    }

}
