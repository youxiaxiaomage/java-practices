package com.yxxmg.mybatisplussample.service;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2022/11/7
 */
public class GlobalConfig {
    private static volatile String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        GlobalConfig.token = token;
    }

}
