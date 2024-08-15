package com.yxxmg.itext.test;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.yxxmg.itext.ItextPdfUtil;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/9
 */
@RunWith(JUnit4.class)
public class ItextTest {
    @Test
    public void test() throws Exception {
        String source = "C:/Users/youxi/Desktop/赵炎_体检报告.pdf";
        String target = "C:/Users/youxi/Desktop/赵炎_体检报告_1.pdf";

        ItextPdfUtil.addWaterMark(source, target, "yxxmg");
    }

    @Test
    public void test1() throws IOException {
        String target = "C:/Users/youxi/Desktop/赵炎_体检报告_1.pdf";
        boolean b = ItextPdfUtil.hasWaterMark(target);
        Assert.assertFalse(b);
    }

}
