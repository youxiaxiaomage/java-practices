package com.yxxmg.collection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/18
 */
@RunWith(JUnit4.class)
public class CollectionTest {
    @Test
    public void test() {
        // List<String> l1 = Arrays.asList("1", "2");
        // l1.add("2");
        // System.out.println(l1);
        // List<String> l2 = Lists.newArrayList("1", "2");
        // l2.add("3");
        // System.out.println(l2);

        // List<String> l3 = Stream.of("1", "2").collect(Collectors.toList());
        // l3.add("3");
        // System.out.println(l3);
        // List<String> l4 = Arrays.stream(new String[] {"1", "2"}).collect(Collectors.toList());
        // l4.add("3");
        // System.out.println(l4);
        List<String> l5 = new ArrayList<>(Arrays.asList("1", "2"));
        l5.add("3");
        System.out.println(l5);
    }

    @Test
    @SneakyThrows
    public void test2() {
        List<Student> studentList =
            Stream.of(new Student("1", "张三"), new Student("2", null)).collect(Collectors.toList());
        Map<String, String> map =
            studentList.stream().map(student -> Pair.of(student.getUserId(), student.getUserName()))
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue, (v1, v2) -> v1));
        System.out.println(map);
    }

    @Data
    @AllArgsConstructor
    public static class Student implements Serializable {
        private static final long serialVersionUID = -3058531222330506251L;
        private String userId;
        private String userName;
    }
}
