package com.yxxmg.jwt.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yxxmg.jwt.config.JwtConfig;
import com.yxxmg.jwt.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NonNull;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/17
 */
@Service
public class TokenService {
    @Resource
    private JwtConfig jwtConfig;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public String generateToken(User user) {
        // claim type
        return Jwts.builder().setId(user.getId()).setSubject(user.getUsername()).setIssuer("yxxmg")
            .setIssuedAt(new Date()).claim("authorities", JSON.toJSONString(user.getAuthorities()))
            .setExpiration(new Date(System.currentTimeMillis() + this.jwtConfig.getExpiration()))
            .signWith(SignatureAlgorithm.HS512, this.jwtConfig.getSecret()).compact();
    }

    public String decodeTokenFromRequest(String token) {
        // DecodedJWT decode =
        return null;
    }

    public boolean isValid(String token, @NonNull UserDetails userDetails) {
        String userName = getUserName(token);
        return userName.equals(userDetails.getUsername());
    }

    public String getUserName(String token) {
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(this.jwtConfig.getSecret()).parseClaimsJws(token).getBody();
    }
}