package com.yxxmg.es.entity;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 索引规则
 * @since : 2023/11/22
 */
@Component("indexProvider")
public class IndexProvider {
    private static final String STUDENT_INDEX_PREFIX = "student-";

    public static String indexName() {
        return indexName(LocalDate.now());
    }

    private static String indexName(LocalDate now) {
        return STUDENT_INDEX_PREFIX + now.toString().replace(":", "-");
    }
}
