package com.yxxmg.gof.test.createpattern.factory;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.yxxmg.gof.create.builder.entity.Student;
import com.yxxmg.gof.create.factory.IDao;
import com.yxxmg.gof.create.factory.ObjectFactory;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 工厂模式测试用例
 * @since : 2023/5/5
 */
@RunWith(JUnit4.class)
public class FactoryTest {

    @Test
    public void test() {
        IDao<Student> studentDao = (IDao<Student>)ObjectFactory.getDaoByName("studentDao");
        Student student = new Student().setId("1").setName("张三").setAge(19);
        boolean result = studentDao.save(student);
        Assert.assertTrue(result);
    }
}
