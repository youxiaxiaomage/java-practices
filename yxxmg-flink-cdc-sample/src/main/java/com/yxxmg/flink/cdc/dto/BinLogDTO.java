package com.yxxmg.flink.cdc.dto;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/8/14
 */
@Data
public class BinLogDTO<T> implements Serializable {
    private static final long serialVersionUID = -6017024190623004497L;
    private T before;
    private T after;
    private Source source;
    private String op;
    @JSONField(name = "ts_ms")
    private String tsMs;
    private String transaction;

    @Data
    static class Source implements Serializable {
        private static final long serialVersionUID = -125806414847874936L;
        private String version;
        private String connector;
        private String name;
        @JSONField(name = "ts_ms")
        private Long tsMs;
        private String snapshot;
        private String db;
        private String sequence;
        private String table;
        @JSONField(name = "server_id")
        private Integer serverId;
        private String gtid;
        private String file;
        private Long pos;
        private Long row;
        private String thread;
        private String query;
    }

}
