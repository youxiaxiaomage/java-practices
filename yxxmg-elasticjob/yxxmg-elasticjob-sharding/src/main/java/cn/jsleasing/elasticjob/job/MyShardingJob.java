package cn.jsleasing.elasticjob.job;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : Sharding Job
 * @since : 2023/1/16
 */
@Slf4j
@Service
public class MyShardingJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("Sharding context:{}", shardingContext);
        switch (shardingContext.getShardingItem()) {
            case 0:
                log.info("分片1：执行任务");
                break;
            case 1:
                log.info("分片2：执行任务");
                break;
            case 2:
                log.info("分片3：执行任务");
                break;
            default:
                break;
        }
    }
}
