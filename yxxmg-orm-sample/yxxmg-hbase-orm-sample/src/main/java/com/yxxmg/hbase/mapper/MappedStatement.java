package com.yxxmg.hbase.mapper;

import com.yxxmg.hbase.command.HqlCommandType;
import com.yxxmg.hbase.config.Configuration;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/20
 */
public final class MappedStatement {
    private String id;
    private Configuration configuration;
    private HqlCommandType hqlCommandType;

    MappedStatement() {}

    public static class Builder {
        private final MappedStatement mappedStatement = new MappedStatement();

        public Builder(Configuration configuration, String id, HqlCommandType hqlCommandType) {
            mappedStatement.configuration = configuration;
            mappedStatement.id = id;
            mappedStatement.hqlCommandType = hqlCommandType;
        }

        // public MappedStatement build() {
        // return mappedStatement;
        // }

        public String id() {
            return mappedStatement.id;
        }
    }
}
