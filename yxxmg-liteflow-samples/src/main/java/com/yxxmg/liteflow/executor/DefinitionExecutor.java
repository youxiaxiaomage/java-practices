package com.yxxmg.liteflow.executor;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/4/20
 */
@Component
@Slf4j
public class DefinitionExecutor {
    @Resource
    private FlowExecutor flowExecutor;

    public void testConfig(String chain) {
        LiteflowResponse response = flowExecutor.execute2Resp(chain, "args");
        // boolean valid = LiteFlowChainELBuilder.validate("THEN(a,b)");
        log.info("response:{}", response);
    }
}
