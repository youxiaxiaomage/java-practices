package com.yxxmg.lock4j.service;

import org.springframework.stereotype.Service;

import com.baomidou.lock.annotation.Lock4j;
import com.yxxmg.lock4j.dto.DemoDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/6/20
 */
@Service
@Slf4j
public class DemoService {
    @Lock4j(keys = {"#demoDTO.userName"})
    public void test(DemoDTO demoDTO) {
        log.info("demoDTO:{}", demoDTO);
    }
}
