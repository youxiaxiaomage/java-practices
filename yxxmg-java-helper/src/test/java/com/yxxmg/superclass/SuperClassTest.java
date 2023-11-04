package com.yxxmg.superclass;

import java.io.Serializable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/28
 */
@RunWith(JUnit4.class)
public class SuperClassTest {
    @Test
    public void test() {
        Parent parent = new Parent();
        parent.setUserId("123456");
        parent.setUserName("admin");
        parent.setNickName("张三");

        Child child = new Child(parent);
        System.out.println(child);
    }

    @Data
    public static class Parent implements Serializable {
        protected String userId;
        protected String userName;
        protected String nickName;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class Child extends Parent implements Serializable {
        private String id;

        public Child(Parent parent) {
            id = parent.getUserId();
            userName = parent.getUserName();
            nickName = parent.getNickName();
        }

        @Override
        public String toString() {
            return "Child{" + "id='" + id + '\'' + ", userName='" + userName + '\'' + ", nickName='" + nickName + '\''
                + '}';
        }
    }

}
