package cn.jsleasing.elasticjob.job;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/1/16
 */
@Service
@Slf4j
public class MySimpleJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("sharding context:{}", shardingContext);
    }
}
