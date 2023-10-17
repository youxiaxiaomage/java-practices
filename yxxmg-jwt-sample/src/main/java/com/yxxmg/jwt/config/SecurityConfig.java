package com.yxxmg.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.yxxmg.jwt.filter.JwtAuthenticationTokenFilter;
import com.yxxmg.jwt.handler.AccessDeniedHandler;
import com.yxxmg.jwt.handler.AuthenticationEntryPoint;
import com.yxxmg.jwt.handler.LogoutHandler;
import com.yxxmg.jwt.handler.LogoutSuccessHandler;

import lombok.RequiredArgsConstructor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : security配置
 * @since : 2023/10/16
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    private final LogoutSuccessHandler logoutSuccessHandler;
    private final LogoutHandler logoutHandler;
    private final AccessDeniedHandler accessDeniedHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    // private final AuthenticationSuccessHandler authenticationSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            // 过滤请求
            .authorizeRequests()
            // 放行接口
            .antMatchers("/user/login").permitAll()
            // 除上面所有请求全部都需要鉴权认证
            .anyRequest().authenticated().and().cors().and().headers().frameOptions().disable().and()
            // csrf禁用
            .csrf().disable()
            // 禁用http响应头
            .headers().cacheControl().disable().and()
            // 基于jwt令牌，无需session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
            // 认证失败 401
            .authenticationEntryPoint(this.authenticationEntryPoint)
            // 未授权 403 通过了认证缺少权限
            .accessDeniedHandler(this.accessDeniedHandler).and()
            // 前置过滤器
            .addFilterBefore(this.jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
            // .formLogin() 这里不能设置成form表单登录
            // .loginProcessingUrl("/user/login")
            // .successHandler(this.authenticationSuccessHandler)
            .logout().logoutUrl("/user/logout")
            // 登出成功处理器
            .logoutSuccessHandler(this.logoutSuccessHandler).addLogoutHandler(this.logoutHandler);
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration)
        throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
