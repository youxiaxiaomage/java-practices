package com.yxxmg.mybatis.flex.config;

import java.util.List;

import com.mybatisflex.core.audit.AuditMessage;
import com.mybatisflex.core.audit.MessageReporter;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/9/1
 */
public class ConsoleMessageReporter implements MessageReporter {
    @Override
    public void sendMessages(List<AuditMessage> messages) {
        for (AuditMessage message : messages) {
            System.out.println(message);
        }
    }
}
