package com.yxxmg.liteflow.component;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

import com.yomahub.liteflow.core.NodeForComponent;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : For循环
 * @since : 2023/4/20
 */
@Component("g")
public class Gcomponent extends NodeForComponent {
    @Override
    public int processFor() throws Exception {
        Thread.sleep(RandomUtils.nextLong(1000, 2000));
        return 1;
    }
}
