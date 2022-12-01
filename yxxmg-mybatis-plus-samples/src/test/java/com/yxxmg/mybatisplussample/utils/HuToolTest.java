package com.yxxmg.mybatisplussample.utils;

import cn.hutool.core.util.DesensitizedUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : HuTool test
 * @since : 2022/11/24
 */
@RunWith(JUnit4.class)
public class HuToolTest {

    @Test
    public void test() {
        String email = DesensitizedUtil.email("110@qq.com");
        System.out.println(email);

        String address = DesensitizedUtil.address("江苏省南京市建邺区中央服务区金融城1号楼1111", 5);
        System.out.println(address);

        // DesensitizedUtil.bankCard();
        // DesensitizedUtil.carLicense();
        String chineseName = DesensitizedUtil.chineseName("张三");
        System.out.println(chineseName);
        // DesensitizedUtil.idCardNum();
        // DesensitizedUtil.password();
        String s = DesensitizedUtil.mobilePhone("15371002835");
        System.out.println(s);
    }
}
