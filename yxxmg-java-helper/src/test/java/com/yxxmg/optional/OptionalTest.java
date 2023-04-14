package com.yxxmg.optional;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import cn.hutool.core.lang.Pair;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : zhaoyan
 * @version : 1.0
 * @description :
 * @since : 2023/4/14
 */
@RunWith(JUnit4.class)
@Slf4j
public class OptionalTest {
    @Test
    public void test() {
        Stream<Teacher> teacherStream = Stream.of(new Teacher().setTeacherId("1").setTeacherName("李老师"),
            new Teacher().setTeacherId("2").setTeacherName("王老师"));
        Map<String, Teacher> teacherMap = teacherStream.map(teacher -> Pair.of(teacher.getTeacherId(), teacher))
            .collect(Collectors.toMap(Pair::getKey, Pair::getValue, (v1, v2) -> v1));
        Student student = new Student().setUserId("123").setTeacherId("");
        String teacherName = Optional.ofNullable(student)
            .map(s -> Optional.ofNullable(teacherMap.get(s.getTeacherId())).map(Teacher::getTeacherName).orElse(""))
            .orElse("");
        Assert.assertTrue(StringUtils.isBlank(teacherName));
        Student student1 = new Student().setUserId("123").setTeacherId("3");
        String teacherName1 = Optional.ofNullable(student1)
            .map(s -> Optional.ofNullable(teacherMap.get(s.getTeacherId())).map(Teacher::getTeacherName).orElse(""))
            .orElse("");
        Assert.assertTrue(StringUtils.isBlank(teacherName1));

    }

    @Test
    @SneakyThrows
    public void test2() {
        Student student = new Student().setUserId("123").setTeacherId("1");
        Stream<Teacher> teacherStream = Stream.of(new Teacher().setTeacherId("1").setTeacherName("李老师"),
            new Teacher().setTeacherId("2").setTeacherName("王老师"));
        Map<String, Teacher> teacherMap = teacherStream.map(teacher -> Pair.of(teacher.getTeacherId(), teacher))
            .collect(Collectors.toMap(Pair::getKey, Pair::getValue, (v1, v2) -> v1));
        // String teacherName = Optional.ofNullable(teacherMap.get(student.getTeacherId())).map(Teacher::getTeacherName)
        // .orElseThrow(() -> new Exception("no data exists"));

        Optional.ofNullable(teacherMap.get(student.getTeacherId())).ifPresent(this::doSomething);

    }

    private void doSomething(Teacher teacher) {
        System.out.println("teacher = " + teacher);
    }

    @Data
    @Accessors(chain = true)
    private static class Student implements Serializable {
        private static final long serialVersionUID = -6323853378949899396L;
        private String userId;
        private String userName;
        private String teacherId;
    }

    @Data
    @Accessors(chain = true)
    private static class Teacher implements Serializable {
        private static final long serialVersionUID = -6323853378949899396L;
        private String teacherId;
        private String teacherName;
    }
}
