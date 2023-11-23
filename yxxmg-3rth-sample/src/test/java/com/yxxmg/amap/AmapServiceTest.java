package com.yxxmg.amap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import lombok.SneakyThrows;

@RunWith(JUnit4.class)
public class AmapServiceTest {
    @SneakyThrows
    @Test
    public void test() {
        String response = AmapService.weatherInfo();
        System.out.println(response);
    }
}