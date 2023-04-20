package com.yxxmg.liteflow.component;

import org.apache.commons.lang3.RandomUtils;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeSwitchComponent;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/4/20
 */
@LiteflowComponent("f")
public class FCmp extends NodeSwitchComponent {
    @Override
    public String processSwitch() throws Exception {
        Thread.sleep(RandomUtils.nextLong(1000, 2000));
        return "tag:dog";
    }
}
