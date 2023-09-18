package com.yxxmg.lombok;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.Serializable;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/4
 */
@RunWith(JUnit4.class)
public class TestData {
    @Test
    public void test() {
        Data of = Data.of("1", "张三");
        Assert.assertEquals("1", of.getId());
    }

    @Test
    public void test2() {
        Book book1 = Book.builder().bookId("123").bookName("book1").build();
        Assert.assertTrue(book1.getIsNew());
    }

    @lombok.Data
    @AllArgsConstructor(staticName = "of", access = AccessLevel.PRIVATE)
    static class Data implements Serializable {
        private static final long serialVersionUID = 699687860242579358L;
        private String id;
        private String name;
    }
}
