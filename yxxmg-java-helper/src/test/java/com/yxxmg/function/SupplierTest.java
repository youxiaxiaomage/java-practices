package com.yxxmg.function;

import java.util.function.Supplier;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : Supplier 测试
 * @since : 2022/12/3
 */
@RunWith(JUnit4.class)
public class SupplierTest {

    @Test
    public void test() {
        Supplier<Integer> integerSupplier = RandomUtils::nextInt;
        System.out.println(integerSupplier.get());
    }

}
