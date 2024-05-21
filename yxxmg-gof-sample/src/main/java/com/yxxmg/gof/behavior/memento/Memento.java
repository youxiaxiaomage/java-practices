package com.yxxmg.gof.behavior.memento;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 备忘录类
 * @since : 2024/5/21
 */
public class Memento {
    private final String state;

    Memento(String state) {
        this.state = state;
    }

    String getState() {
        return state;
    }
}
