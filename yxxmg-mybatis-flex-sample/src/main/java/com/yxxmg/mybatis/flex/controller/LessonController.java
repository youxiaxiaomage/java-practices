package com.yxxmg.mybatis.flex.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.yxxmg.mybatis.flex.api.LessonService;
import com.yxxmg.mybatis.flex.entity.Lesson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * @author : zhaoyan
 * @version : 1.0
 * @description :
 * @since : 2023/8/31
 */
@RestController
@RequestMapping("/lesson")
@Api(value = "课程接口", tags = "课程接口")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @GetMapping
    @ApiOperation("列表")
    public List<Lesson> list() {
        return this.lessonService.list();
    }

    @PostMapping
    @ApiOperation("新增")
    public String add(@RequestBody Lesson lesson) {
        this.lessonService.save(lesson);
        return "success";
    }

    @PutMapping
    @ApiOperation("修改")
    public String update(@RequestBody Lesson lesson) {
        this.lessonService.updateById(lesson);
        return "success";
    }

    @DeleteMapping
    @ApiOperation("删除")
    public String delete(@RequestParam String lessonId) {
        this.lessonService.removeById(lessonId);
        return "success";
    }

}
