package com.yxxmg.liteflow.component;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

import com.yomahub.liteflow.core.NodeIfComponent;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/4/20
 */
@Component("x")
public class Xcomponent extends NodeIfComponent {
    @Override
    public boolean processIf() throws Exception {
        Thread.sleep(RandomUtils.nextLong(1000, 2000));
        return true;
    }
}
