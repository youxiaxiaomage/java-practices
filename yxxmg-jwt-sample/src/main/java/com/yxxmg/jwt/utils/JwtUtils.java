package com.yxxmg.jwt.utils;

import com.alibaba.fastjson.JSON;
import com.yxxmg.jwt.config.JwtConfig;
import com.yxxmg.jwt.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/16
 */
@Component
public class JwtUtils {
    private static  JwtConfig jwtConfig;
    @Autowired
    public void setJwtConfig(JwtConfig jwtConfig) {
        JwtUtils.jwtConfig = jwtConfig;
    }
    public static String createAccessToken(User user) {
        return Jwts
                .builder()
                .setId(user.getId())
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setIssuer("yxxmg")
                .claim("authorities", JSON.toJSONString(user.getAuthorities()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
                .compact();

    }
}
