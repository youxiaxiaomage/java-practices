package com.yxxmg.liteflow.component;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

import com.yomahub.liteflow.core.NodeSwitchComponent;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/4/20
 */
@Slf4j
@Component("d")
public class DCmp extends NodeSwitchComponent {

    @Override
    public String processSwitch() throws Exception {
        Thread.sleep(RandomUtils.nextLong(1000, 2000));
        return "b";
    }
}
