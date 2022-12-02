package com.yxxmg.mybatisplussample;

import com.yxxmg.pay.spi.PayService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = MybatisPlusSampleApplication.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class MybatisPlusSampleApplicationTest {
    @Resource
    private PayService payService;

    @Test
    public void testPayService() {
        String pay = this.payService.pay("iPhone14", Float.parseFloat("6999.99"));
        System.out.println(pay);
        Assert.assertTrue(pay.contains("银联卡"));
    }
}