package com.yxxmg.oss.controller;

import com.yxxmg.oss.api.OssService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/4/21
 */
@RestController
@RequestMapping("/oss")
@RequiredArgsConstructor
public class OssController {
    private final OssService ossService;
    @GetMapping("/test")
    public String test() {
        return this.ossService.test();
    }
}
