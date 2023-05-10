package com.yxxmg.gof.relation.command;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : TODO
 * @since : 2023/5/10
 */
public class SpringProcessEngineConfiguration extends ProcessEngineConfigurationImpl {
    protected TaskService taskService = new TaskServiceImpl(this);

    public void init() {
        initCommandExecutors();
        initServices();
    }

    private void initCommandExecutors() {
        initCommandInterceptors();
        initCommandExecutor();
    }

    public void initServices() {
        initService(taskService);
    }

    private void initService(TaskService taskService) {
        if (taskService instanceof CommonEngineServiceImpl) {
            ((CommonEngineServiceImpl)taskService).setCommandExecutor(commandExecutor);
        }
    }
}
