package com.yxxmg.flowable.command;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.ManagementService;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 动态跳转
 * @since : 2023/6/4
 */
@AllArgsConstructor
public class DynamicJumpService {
    protected ManagementService managementService;

    public void executeJump(String processInstanceId, String fromActivityId, String toActivityId) {
        DynamicJumpCmd dynamicJumpCmd = new DynamicJumpCmd(processInstanceId, fromActivityId, toActivityId);
        this.managementService.executeCommand(dynamicJumpCmd);
    }
}
