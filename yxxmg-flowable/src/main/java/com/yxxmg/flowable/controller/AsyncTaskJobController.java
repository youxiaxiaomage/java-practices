package com.yxxmg.flowable.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yxxmg.flowable.api.AsyncTaskJob;

import lombok.RequiredArgsConstructor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : xxl-job配置的调度任务
 * @since : 2024/6/24
 */
@RestController("/job")
@RequiredArgsConstructor
public class AsyncTaskJobController {
    private final AsyncTaskJob asyncTaskJob;

    @GetMapping("/executeJobs")
    public void executeJobs() {
        this.asyncTaskJob.execute();
    }

}
