package com.yxxmg.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : function函数
 * @since : 2023/9/22
 */
public class StringConvert {
    public static void convert(List<String> nameList, Function<String, String> function) {
        nameList.forEach(name -> {
            String apply = function.apply(name);
            System.out.println(apply);
        });
    }

    public static void main(String[] args) {
        List<String> nameList = Arrays.asList("ZhangSan", "LiSi");
        convert(nameList, String::toLowerCase);
        System.out.println(nameList);
        convert(nameList, String::toUpperCase);
        System.out.println(nameList);
    }
}
