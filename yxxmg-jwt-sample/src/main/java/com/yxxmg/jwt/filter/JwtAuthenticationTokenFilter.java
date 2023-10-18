package com.yxxmg.jwt.filter;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.yxxmg.jwt.config.JwtConfig;
import com.yxxmg.jwt.service.TokenService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 自定义jwt过滤器
 * @since : 2023/10/16
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    private JwtConfig jwtConfig;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        // 若是白名单不检查
        String tokenHeader = request.getHeader(this.jwtConfig.getTokenHeader());
        if (StringUtils.isNotBlank(tokenHeader) && tokenHeader.startsWith(this.jwtConfig.getTokenPrefix())) {
            String authToken = tokenHeader.substring(this.jwtConfig.getTokenPrefix().length());
            String userName = this.tokenService.getUserName(authToken);
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
            if (this.tokenService.isValid(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
