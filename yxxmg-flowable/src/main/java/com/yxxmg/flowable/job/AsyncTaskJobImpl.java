package com.yxxmg.flowable.job;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.flowable.engine.ManagementService;
import org.flowable.job.api.Job;
import org.springframework.stereotype.Service;

import com.yxxmg.flowable.api.AsyncTaskJob;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/6/24
 */
@Service
public class AsyncTaskJobImpl implements AsyncTaskJob {
    @Resource
    private ManagementService managementService;

    @Override
    public void execute() {
        // TODO 分布式锁
        List<Job> jobList = managementService.createJobQuery().list();
        if (CollectionUtils.isNotEmpty(jobList)) {
            for (Job job : jobList) {
                managementService.executeJob(job.getId());
            }
        }
    }
}
