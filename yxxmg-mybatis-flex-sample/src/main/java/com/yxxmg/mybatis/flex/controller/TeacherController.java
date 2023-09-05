package com.yxxmg.mybatis.flex.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.yxxmg.mybatis.flex.api.TeacherService;
import com.yxxmg.mybatis.flex.entity.Teacher;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * @author : zhaoyan
 * @version : 1.0
 * @description :
 * @since : 2023/8/31
 */
@Api(value = "教师接口", tags = "教师接口")
@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    @ApiOperation("列表")
    public List<Teacher> list() {
        return this.teacherService.list();
    }

    @PostMapping
    @ApiOperation("新增")
    public String add(@RequestBody Teacher teacher) {
        // AR模式
        boolean save = teacher.save();
        System.out.println(save);
        return "success";
    }

    @PutMapping
    @ApiOperation("修改")
    public String update(@RequestBody Teacher teacher) {
        this.teacherService.updateById(teacher);
        return "success";
    }

    @DeleteMapping
    @ApiOperation("删除")
    public String delete(@RequestParam String teacherId) {
        this.teacherService.removeById(teacherId);
        return "success";
    }

}
