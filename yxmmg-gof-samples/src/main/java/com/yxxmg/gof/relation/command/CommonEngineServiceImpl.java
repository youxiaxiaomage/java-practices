package com.yxxmg.gof.relation.command;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/10
 */
public abstract class CommonEngineServiceImpl<C extends AbstractEngineConfiguration> extends CommonServiceImpl<C> {

    protected CommandExecutor commandExecutor;

    public CommonEngineServiceImpl() {}

    public CommonEngineServiceImpl(C configuration) {
        super(configuration);
    }

    public CommandExecutor getCommandExecutor() {
        return commandExecutor;
    }

    public void setCommandExecutor(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

}
