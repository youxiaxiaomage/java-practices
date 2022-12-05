package com.yxxmg.flowable;

import org.flowable.engine.*;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : TODO
 * @since : 2022/12/5
 */
public class Test {
    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        TaskService taskService = processEngine.getTaskService();
        ManagementService managementService = processEngine.getManagementService();
        IdentityService identityService = processEngine.getIdentityService();
        HistoryService historyService = processEngine.getHistoryService();
        FormService formService = processEngine.getFormService();
        DynamicBpmnService dynamicBpmnService = processEngine.getDynamicBpmnService();
    }
}
