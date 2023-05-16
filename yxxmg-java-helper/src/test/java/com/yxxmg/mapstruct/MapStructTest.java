package com.yxxmg.mapstruct;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/16
 */
@RunWith(JUnit4.class)
public class MapStructTest extends TestCase {

    @Test
    public void test1() {
        Person person = new Person().setId("1").setName("张三").setCreateTime(new Date()).setHeight(10.11)
            .setDescribe("描述信息").setSource(BigDecimal.valueOf(999.999)).setAge(14);
        PersonDTO personDTO = PersonDTO.convert(person);
        assertNull(personDTO.getId());
    }

    @Test
    public void test2() {
        Person person = new Person().setId("1").setName("张三").setCreateTime(new Date()).setHeight(10.11)
            .setSource(BigDecimal.valueOf(999.999)).setAge(14);
        PersonDTO personDTO = PersonDTO.convert2(person);
        assertEquals("默认值", personDTO.getDescribe());
    }

    @Test
    public void test3() {
        Person person = new Person().setId("1").setName("张三").setCreateTime(new Date()).setHeight(10.11)
            .setSource(BigDecimal.valueOf(999.999)).setAge(14);
        PersonDTO personDTO = PersonDTO.convert2(person);
        System.out.println(personDTO);
    }

    @Test
    public void test4() {
        Person person = new Person().setId("1").setName("张三").setCreateTime(new Date()).setHeight(10.11)
            .setSource(BigDecimal.valueOf(999.999)).setAge(14);
        PersonDTO personDTO = PersonDTO.convert2(person);
        System.out.println(personDTO);
    }

    @Test
    public void test5() {
        Person person = new Person().setId("1").setName("张三").setCreateTime(new Date()).setHeight(10.11)
            .setSource(BigDecimal.valueOf(999.999)).setAge(14);
        PersonDTO personDTO = PersonDTO.convert2(person);
        System.out.println(personDTO);
    }

    @Test
    public void test6() {
        Person person = new Person().setId("1").setName("张三").setCreateTime(new Date()).setHeight(10.11)
            .setSource(BigDecimal.valueOf(999.999)).setAge(14);
        PersonDTO personDTO = PersonDTO.convert2(person);
        System.out.println(personDTO);
    }
}
