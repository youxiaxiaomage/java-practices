package com.yxxmg.gof.relation.command;

import cn.hutool.core.lang.UUID;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/10
 */
public class TaskServiceImpl extends CommonEngineServiceImpl<ProcessEngineConfigurationImpl> implements TaskService {
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
