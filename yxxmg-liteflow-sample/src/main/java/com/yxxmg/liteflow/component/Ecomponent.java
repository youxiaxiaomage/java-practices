package com.yxxmg.liteflow.component;

import org.apache.commons.lang3.RandomUtils;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/4/20
 */
@LiteflowComponent("e")
@Slf4j
public class Ecomponent extends NodeComponent {
    @Override
    public void process() throws Exception {
        log.info("e..............");
        Thread.sleep(RandomUtils.nextLong(1000, 2000));
    }
}
