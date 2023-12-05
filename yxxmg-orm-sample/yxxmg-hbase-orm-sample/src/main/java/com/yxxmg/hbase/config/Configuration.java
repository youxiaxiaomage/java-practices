package com.yxxmg.hbase.config;

import com.yxxmg.hbase.mapper.MappedStatement;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/20
 */
public class Configuration {
    public boolean hasStatement(String statementId) {
        return false;
    }

    public MappedStatement getMappedStatement(String statementId) {
        return null;
    }
}
