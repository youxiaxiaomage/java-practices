package com.yxxmg.gof.behavior.command;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 开灯命令
 * @since : 2024/6/5
 */
public class LightOnCommand implements Command {
    private final Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}
