package com.yxxmg.jwt.handler;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yxxmg.jwt.config.JwtConfig;
import com.yxxmg.jwt.service.TokenService;
import com.yxxmg.jwt.vo.ResponseVO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/16
 */
@Service
@Slf4j
public class LogoutSuccessHandler
    implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {
    @Resource
    private TokenService tokenService;
    @Resource
    private JwtConfig jwtConfig;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException, ServletException {

        String tokenHeader = request.getHeader(this.jwtConfig.getTokenHeader());
        if (StringUtils.isNotBlank(tokenHeader) && tokenHeader.startsWith(this.jwtConfig.getTokenPrefix())) {
            log.info("Trigger logout success {}", authentication);
            String authToken = tokenHeader.substring(this.jwtConfig.getTokenPrefix().length());
            String userName = this.tokenService.getUserName(authToken);
            this.tokenService.removeToken(userName);
            SecurityContextHolder.clearContext();
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control", "no-cache");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpStatus.OK.value());
            response.getWriter().println(JSON.toJSONString(ResponseVO.success("登出成功")));
            response.getWriter().flush();
        } else {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control", "no-cache");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.getWriter().println(JSON.toJSONString(ResponseVO.success("非法登出操作")));
            response.getWriter().flush();
        }

    }
}
