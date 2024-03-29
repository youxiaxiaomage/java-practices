package com.yxxmg.mybatis.flex.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.activerecord.Model;
import com.mybatisflex.core.keygen.KeyGenerators;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : zhaoyan
 * @version : 1.0
 * @description :
 * @since : 2023/8/31
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table("TEST_TEACHER")
@ApiModel("教师")
public class Teacher extends Model<Teacher> {
    private static final long serialVersionUID = 6142633632797255382L;
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    @Column("PK_TEACHER")
    @ApiModelProperty("教师主键")
    private String pkTeacher;
    @Column("TEACHER_NAME")
    @ApiModelProperty("教师名称")
    private String teacherName;
}
