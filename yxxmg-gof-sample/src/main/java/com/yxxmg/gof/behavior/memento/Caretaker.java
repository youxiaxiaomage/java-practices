package com.yxxmg.gof.behavior.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 管理者
 * @since : 2024/5/21
 */
public class Caretaker {
    private List<Memento> mementoList = new ArrayList<>();

    public void addMemento(Memento memento) {
        mementoList.add(memento);
    }

    public Memento getMemento(int index) {
        return mementoList.get(index);
    }
}
