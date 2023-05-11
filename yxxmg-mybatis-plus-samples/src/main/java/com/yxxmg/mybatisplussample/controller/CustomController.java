package com.yxxmg.mybatisplussample.controller;

import com.yxxmg.mybatisplussample.api.CustomService;
import com.yxxmg.mybatisplussample.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2022/11/7
 */
@RestController
@RequestMapping("/custom")
@Api(value = "自定义", tags = "自定义测试")
public class CustomController {

    private CustomService customService;

    @Inject
    public void setCustomService(CustomService customService) {
        this.customService = customService;
    }

    @ApiOperation("自定义测试")
    @GetMapping
    public List<User> custom() {
        return this.customService.custom();
    }
}
