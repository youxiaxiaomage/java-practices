package com.yxxmg.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yxxmg.entity.Teacher;
import com.yxxmg.mapper.TeacherMapper;
import com.yxxmg.service.TeacherService;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/9/10
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public Teacher getTeacherBydId(String id) {
        return this.teacherMapper.getById(id);
    }
}
