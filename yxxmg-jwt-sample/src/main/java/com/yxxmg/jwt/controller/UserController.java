package com.yxxmg.jwt.controller;

import com.yxxmg.jwt.api.LoginService;
import com.yxxmg.jwt.dto.LoginDTO;
import com.yxxmg.jwt.vo.ResponseVO;
import com.yxxmg.jwt.vo.TokenVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/16
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final LoginService loginService;
    @PostMapping("/login")
    public ResponseVO<TokenVO> login(@Validated @RequestBody LoginDTO loginDTO) {
        return ResponseVO.success("请求成功", this.loginService.login(loginDTO));
    }

}
