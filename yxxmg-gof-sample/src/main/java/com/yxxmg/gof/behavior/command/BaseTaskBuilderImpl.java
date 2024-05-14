package com.yxxmg.gof.behavior.command;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/10
 */
public abstract class BaseTaskBuilderImpl implements TaskBuilder {
    protected CommandExecutor commandExecutor;
    protected String id;
    protected String name;

    public BaseTaskBuilderImpl(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    /**
     * 创建人物
     * 
     * @return Task
     */
    @Override
    public abstract Task create();

    @Override
    public TaskBuilder id(String id) {
        this.id = id;
        return this;
    }

    @Override
    public TaskBuilder name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}