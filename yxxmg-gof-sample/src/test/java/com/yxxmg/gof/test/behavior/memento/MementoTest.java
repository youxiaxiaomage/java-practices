package com.yxxmg.gof.test.behavior.memento;

import org.junit.Test;

import com.yxxmg.gof.behavior.memento.Caretaker;
import com.yxxmg.gof.behavior.memento.Originator;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 备忘录模式
 * @since : 2024/5/21
 */
public class MementoTest {
    @Test
    public void test() {
        // 定义出发起人
        Originator originator = new Originator();
        originator.setState("初始状态");
        // 定义出备忘录管理员
        Caretaker caretaker = new Caretaker();
        // 创建一个备忘录
        caretaker.addMemento(originator.saveStateToMemento());
        originator.setState("制作中");
        // 恢复一个备忘录
        originator.getStateFromMemento(caretaker.getMemento(0));
    }
}
