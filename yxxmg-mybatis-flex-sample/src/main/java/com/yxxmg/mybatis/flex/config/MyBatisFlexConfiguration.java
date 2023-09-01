package com.yxxmg.mybatis.flex.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.mybatisflex.core.audit.AuditManager;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : TODO
 * @since : 2023/9/1
 */
@Configuration
public class MyBatisFlexConfiguration {
    private static final Logger logger = LoggerFactory.getLogger("mybatis-flex-sql");

    public MyBatisFlexConfiguration() {
        // 开启审计功能
        AuditManager.setAuditEnable(true);

        // 设置 SQL 审计收集器
        AuditManager.setMessageCollector(
            auditMessage -> logger.info("{},{}ms", auditMessage.getFullSql(), auditMessage.getElapsedTime()));
    }
}
