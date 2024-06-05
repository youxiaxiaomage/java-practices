package com.yxxmg.gof.test.behavior.command;

import org.junit.Test;

import com.yxxmg.gof.behavior.command.*;

import junit.framework.TestCase;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 命令模式测试用例
 * @since : 2023/5/10
 */
public class CommandTest extends TestCase {
    @Test
    public void test() {
        // 创建电灯对象
        Light light = new Light();

        // 创建命令对象
        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);

        // 创建遥控器对象
        RemoteControl remoteControl = new RemoteControl();

        // 设置命令
        remoteControl.setCommand(lightOn);
        // 按下按钮，执行命令
        remoteControl.pressButton();// 输出: Light is on

        // 更换命令
        remoteControl.setCommand(lightOff);
        // 再次按下按钮，执行新的命令
        remoteControl.pressButton(); // 输出: Light is off
    }
}
