package com.yxxmg.gof.behavior.memento;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 发起人
 * @since : 2024/5/21
 */
public class Originator {
    private String state;

    public void setState(String state) {
        this.state = state;
        System.out.println("State set to: " + state);
    }

    public String getState() {
        return state;
    }

    public Memento saveStateToMemento() {
        return new Memento(state);
    }

    public void getStateFromMemento(Memento memento) {
        state = memento.getState();
    }
}
