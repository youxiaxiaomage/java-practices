package com.yxxmg.liteflow.component;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

import com.yomahub.liteflow.core.NodeComponent;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/4/20
 */
@Component("a")
@Slf4j
public class ACmp extends NodeComponent {
    @Override
    public void process() throws Exception {
        // CustomContext customContext = this.getContextBean(CustomContext.class);
        log.info("a.........");
        Thread.sleep(RandomUtils.nextLong(1000, 2000));
    }
}
