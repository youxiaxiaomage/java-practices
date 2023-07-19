package com.yxxmg.liteflow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yxxmg.liteflow.executor.DefinitionExecutor;

import lombok.RequiredArgsConstructor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/4/20
 */
@RestController
@RequestMapping("/flow")
@RequiredArgsConstructor
public class FlowController {
    private final DefinitionExecutor definitionExecutor;

    @GetMapping("/test")
    public void test(@RequestParam String chain) {
        this.definitionExecutor.testConfig(chain);

    }
}
