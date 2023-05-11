package com.yxxmg.mybatisplussample.controller;

import com.xkcoding.justauth.AuthRequestFactory;
import lombok.RequiredArgsConstructor;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2022/11/24
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final static Logger LOG = LoggerFactory.getLogger(AuthController.class);
    private final AuthRequestFactory authRequestFactory;

    @GetMapping
    public Map<String, String> loginType() {
        List<String> oauthList = authRequestFactory.oauthList();
        return oauthList.stream().collect(Collectors.toMap(oauth -> oauth.toLowerCase() + "登录",
            oauth -> "http://oauth.xkcoding.com/demo/oauth/login/" + oauth.toLowerCase()));
    }

    @GetMapping("/login/{oauthType}")
    public void loginType(@PathVariable String oauthType, HttpServletResponse httpServletResponse) throws IOException {
        LOG.info("oauthType:{}", oauthType);
        AuthRequest authRequest = authRequestFactory.get(oauthType);
        LOG.info("authRequest:{}", authRequest);
        httpServletResponse.sendRedirect(authRequest.authorize(oauthType + "::" + AuthStateUtils.createState()));
    }
}
