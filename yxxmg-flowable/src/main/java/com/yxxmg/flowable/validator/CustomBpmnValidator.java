package com.yxxmg.flowable.validator;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.flowable.bpmn.model.*;
import org.flowable.bpmn.model.Process;
import org.flowable.validation.ValidationError;
import org.flowable.validation.validator.ValidatorImpl;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : BPMN模型校验器 只有模型deploy时会生效
 * @since : 2024/6/24
 */
public class CustomBpmnValidator extends ValidatorImpl {
    @Override
    public void validate(BpmnModel bpmnModel, List<ValidationError> errorList) {
        Process mainProcess = bpmnModel.getMainProcess();
        List<UserTask> userTasks = mainProcess.findFlowElementsOfType(UserTask.class);
        List<ServiceTask> serviceTasks = mainProcess.findFlowElementsOfType(ServiceTask.class);
        List<StartEvent> startEvents = mainProcess.findFlowElementsOfType(StartEvent.class);
        List<EndEvent> endEvents = mainProcess.findFlowElementsOfType(EndEvent.class);
        // 只检查是否存在开始节点和节数节点
        if (CollectionUtils.isEmpty(startEvents) || CollectionUtils.isEmpty(endEvents)) {
            addError(errorList, "流程必须存在开始或结束节点", mainProcess, "流程必须存在开始或结束节点");
        }

    }
}
