package com.yxxmg.mybatis.flex.service;

import org.springframework.stereotype.Service;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.yxxmg.mybatis.flex.api.LessonService;
import com.yxxmg.mybatis.flex.entity.Lesson;
import com.yxxmg.mybatis.flex.mapper.LessonMapper;

/**
 * @author : zhaoyan
 * @version : 1.0
 * @description :
 * @since : 2023/8/31
 */
@Service
public class LessonServiceImpl extends ServiceImpl<LessonMapper, Lesson> implements LessonService {}
