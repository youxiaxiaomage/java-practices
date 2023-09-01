package com.yxxmg.mybatis.flex.entity;

import java.io.Serializable;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : zhaoyan
 * @version : 1.0
 * @description :
 * @since : 2023/8/31
 */

@Data
@Table("TEST_LESSON")
@ApiModel("课程")
public class Lesson implements Serializable {
    private static final long serialVersionUID = 7535668768608806808L;
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    @ApiModelProperty("课程主键")
    @Column("PK_LESSON")
    private String pkLesson;
    @Column("LESSON_NAME")
    @ApiModelProperty("课程名称")
    private String lessonName;
    @Column("PK_TEACHER")
    @ApiModelProperty("教师主键")
    private String pkTeacher;
}
