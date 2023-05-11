package com.yxxmg.gof.relation.command;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/10
 */
public class TaskServiceImpl extends AbstractCommonEngineServiceImpl<ProcessEngineConfigurationImpl>
    implements TaskService {
    public TaskServiceImpl(ProcessEngineConfigurationImpl processEngineConfiguration) {
        super(processEngineConfiguration);
    }

    @Override
    public Task newTask() {
        return newTask(null);
    }

    @Override
    public Task newTask(String taskId) {
        return commandExecutor.execute(new NewTaskCmd(taskId));
    }

}
