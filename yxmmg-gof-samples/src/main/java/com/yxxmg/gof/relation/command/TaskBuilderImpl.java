package com.yxxmg.gof.relation.command;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : TODO
 * @since : 2023/5/10
 */
public class TaskBuilderImpl extends CommonServiceImpl {

    TaskBuilderImpl(CommandExecutor commandExecutor) {
        super(commandExecutor);
    }

    // @Override
    // public Task create() {
    // return commandExecutor.execute(new CreateTaskCmd(this));
    // }
}
