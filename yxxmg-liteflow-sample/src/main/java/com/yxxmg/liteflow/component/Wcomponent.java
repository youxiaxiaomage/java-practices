package com.yxxmg.liteflow.component;

import org.springframework.stereotype.Component;

import com.yomahub.liteflow.core.NodeWhileComponent;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : While
 * @since : 2023/4/20
 */
@Component("w")
public class Wcomponent extends NodeWhileComponent {
    @Override
    public boolean processWhile() throws Exception {
        // 这边设置true 死循环
        return true;
    }
}
