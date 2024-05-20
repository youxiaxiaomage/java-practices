package com.yxxmg.gof.create.builder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.yxxmg.gof.create.builder.entity.User;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/20
 */
@RunWith(JUnit4.class)
public class UserBuilderTest {
    @Test
    public void test() {
        User user = User.builder().gender(1).userId("1").userName("test").build();
        System.out.println(user);
    }
}
