package com.yxxmg.jwt.controller;

import com.yxxmg.jwt.entity.Dept;
import com.yxxmg.jwt.vo.ResponseVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/16
 */
@RestController
@RequestMapping("/dept")
public class DeptController {
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseVO<List<Dept>> list() {
        return ResponseVO.success("请求成功", Collections.emptyList());
    }
}
