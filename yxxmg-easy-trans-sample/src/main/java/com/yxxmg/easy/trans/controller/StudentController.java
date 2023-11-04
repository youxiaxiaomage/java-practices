package com.yxxmg.easy.trans.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yxxmg.easy.trans.api.StudentService;
import com.yxxmg.easy.trans.entity.Student;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/11/2
 */
@RestController
@RequestMapping("/stu")
public class StudentController {
    @Resource
    private StudentService studentService;

    @GetMapping("/list")
    public List<Student> list() {
        return this.studentService.list();
    }
}
