package com.yxxmg.gof.relation.command;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : TODO
 * @since : 2023/5/10
 */
public class CommandExecutorImpl implements CommandExecutor {
    public CommandInterceptor getFirst() {
        return first;
    }

    public void setFirst(CommandInterceptor first) {
        this.first = first;
    }

    protected CommandInterceptor first;

    public CommandExecutorImpl(CommandInterceptor first) {
        this.first = first;
    }

    @Override
    public <T> T execute(Command<T> command) {
        return first.execute(command);
    }
}
