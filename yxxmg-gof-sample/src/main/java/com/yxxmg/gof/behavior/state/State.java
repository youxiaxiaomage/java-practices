package com.yxxmg.gof.behavior.state;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 状态模式
 * @since : 2024/5/23
 */
public class State {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("\\$\\{([^}]*)}");
        Matcher matcher = pattern.matcher("${appCode}");
        if (matcher.find()) {
            System.out.println(matcher.group(1));
        } else {
            System.out.println("not found");
        }
    }
}
