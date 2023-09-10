package com.yxxmg.mapper;

import org.apache.ibatis.annotations.Param;

import com.yxxmg.entity.Teacher;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/9/10
 */
public interface TeacherMapper {
    Teacher getById(@Param("id") String id);
}
