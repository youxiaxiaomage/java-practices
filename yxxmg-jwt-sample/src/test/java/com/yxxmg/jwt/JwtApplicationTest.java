package com.yxxmg.jwt;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootTest
@Slf4j
public class JwtApplicationTest {
    @Test
    public void passwordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("123456");
        log.info("加密密文：{}", password);
        boolean matches = passwordEncoder.matches("123456", password);
        log.info("是否匹配：{}", matches);
    }

}