package com.yxxmg.sbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import cn.hutool.core.convert.Convert;

/**
 * @author : zhaoyan
 * @version : 1.0
 * @description : 全角转半角
 * @since : 2023/3/23
 */
@RunWith(JUnit4.class)
public class SbcTest {
    @Test
    public void test() {
        String a = "() （）　（）";

        // 结果为"123456789"
        System.out.println(a);
        String dbc = Convert.toDBC(a);
        System.out.println(dbc);
    }
}
