package com.yxxmg.easy.trans.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxxmg.easy.trans.api.StudentService;
import com.yxxmg.easy.trans.entity.Student;
import com.yxxmg.easy.trans.mapper.StudentMapper;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/11/2
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {}
