package com.yxxmg.gof.relation.command;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/10
 */
public class TaskBuilderImpl extends AbstractCommonServiceImpl {

    TaskBuilderImpl(CommandExecutor commandExecutor) {
        super(commandExecutor);
    }

    // @Override
    // public Task create() {
    // return commandExecutor.execute(new CreateTaskCmd(this));
    // }
}
