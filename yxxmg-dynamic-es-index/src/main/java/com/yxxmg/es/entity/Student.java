package com.yxxmg.es.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 学生对象
 * @since : 2023/11/22
 */
@Data
@Document(indexName = "#{@indexProvider.indexName()}") // 自动创建索引 7以前不用加@，7之后必须加@才能识别动态的
public class Student implements Serializable {
    private static final long serialVersionUID = 2509479768838581513L;
    @Id
    @Field(name = "id", type = FieldType.Text)
    private String studentId;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String studentName;

}
