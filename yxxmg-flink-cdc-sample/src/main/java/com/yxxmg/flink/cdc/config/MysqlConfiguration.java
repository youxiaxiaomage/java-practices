package com.yxxmg.flink.cdc.config;

import com.google.common.collect.Sets;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/8/14
 */
@Configuration
@ConfigurationProperties(prefix = "yxxmg.mysql")
@Data
public class MysqlConfiguration {

    private String host;
    private Integer port;
    private String username;
    private String password;
    private List<String> databases;
    private List<String> tables;


    public  String[] tables() {
        Set<String> set = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(databases)) {
            for (String database : databases) {
                if (CollectionUtils.isNotEmpty(databases)) {
                    for (String table : tables) {
                        set.add(database + "." + table);
                    }
                }
            }
        }
        return set.toArray(new String[0]);
    }
}
