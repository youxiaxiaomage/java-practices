package com.yxxmg.gof.relation.command;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/10
 */
public abstract class AbstractCommonEngineServiceImpl<C extends AbstractEngineConfiguration>
    extends AbstractCommonServiceImpl<C> {

    protected CommandExecutor commandExecutor;

    public AbstractCommonEngineServiceImpl() {}

    public CommandExecutor getCommandExecutor() {
        return commandExecutor;
    }

    public void setCommandExecutor(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

}
