package com.yxxmg.springboot.samples.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yxxmg.springboot.samples.service.TestService;

import lombok.RequiredArgsConstructor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2022/12/5
 */
@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @GetMapping("/test")
    public String test(@RequestParam("param") String param) {
        return this.testService.test(param);
    }
}
