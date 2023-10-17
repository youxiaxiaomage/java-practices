// package com.yxxmg.jwt.handler;
//
// import java.io.IOException;
//
// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
//
// import org.springframework.http.HttpStatus;
// import org.springframework.security.core.Authentication;
// import org.springframework.stereotype.Component;
//
// import com.alibaba.fastjson.JSON;
// import com.alibaba.fastjson.serializer.SerializerFeature;
// import com.yxxmg.jwt.vo.ResponseVO;
//
// import lombok.extern.slf4j.Slf4j;
//
/// **
// * @author : yxxmg
// * @version : 1.0
// * @description :
// * @since : 2023/10/16
// */
// @Component
// @Slf4j
// public class AuthenticationSuccessHandler
// implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {
//
// @Override
// public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
// Authentication authentication) throws IOException, ServletException {
// log.info("Authentication success {}", authentication);
// response.setHeader("Access-Control-Allow-Origin", "*");
// response.setHeader("Cache-Control", "no-cache");
// response.setContentType("application/json");
// response.setCharacterEncoding("UTF-8");
// response.setStatus(HttpStatus.OK.value());
// response.getWriter()
// .println(JSON.toJSONString(ResponseVO.success("登录成功"), SerializerFeature.WriteMapNullValue));
// response.getWriter().flush();
//
// }
// }
