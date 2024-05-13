package com.yxxmg.mapstruct;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;

import junit.framework.TestCase;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/16
 */
public class MapStructTest extends TestCase {

    public void test1() {
        Person person = new Person().setId("1").setName("张三").setCreateTime(new Date()).setHeight(10.11)
            .setDescribe("描述信息").setSource(BigDecimal.valueOf(999.999)).setAge(14);
        PersonDTO personDTO = PersonDTO.convert(person);
        assertNull(personDTO.getId());
    }

    public void test2() {
        Person person = new Person().setId("1").setName("张三").setCreateTime(new Date()).setHeight(10.11)
            .setSource(BigDecimal.valueOf(999.999)).setAge(14);
        PersonDTO personDTO = PersonDTO.convert2(person);
        assertEquals("默认值", personDTO.getDescribe());
    }

    public void test3() {
        Person person = new Person().setId("1").setName("张三").setCreateTime(new Date()).setHeight(10.11)
            .setSource(BigDecimal.valueOf(999.999)).setAge(14);
        PersonDTO personDTO = PersonDTO.convert2(person);
        System.out.println(personDTO);
    }

    public void test4() {
        Person person = new Person().setId("1").setName("张三").setCreateTime(new Date()).setHeight(10.11)
            .setSource(BigDecimal.valueOf(999.999)).setAge(14);
        PersonDTO personDTO = PersonDTO.convert2(person);
        System.out.println(personDTO);
    }

    public void test5() {
        Person person = new Person().setId("1").setName("张三").setCreateTime(new Date()).setHeight(10.11)
            .setSource(BigDecimal.valueOf(999.999)).setAge(14);
        PersonDTO personDTO = PersonDTO.convert2(person);
        System.out.println(personDTO);
    }

    public void test6() {
        Person person = new Person().setId("1").setName("张三").setCreateTime(new Date()).setHeight(10.11)
            .setSource(BigDecimal.valueOf(999.999)).setAge(14);
        PersonDTO personDTO = PersonDTO.convert2(person);
        System.out.println(personDTO);
    }

    public void test7() {
        // 高级特性 迭代器@IterableMapping、上下文@Context
        MenuDTO menuDTO1 = new MenuDTO().setMenuId("1").setMenuName("子菜单1");
        MenuDTO menuDTO2 = new MenuDTO().setMenuId("2").setMenuName("子菜单2");
        String parentId = "parentId";
        // 如何设置子菜单menuDTO1以及menuDTO2设置为parentId
        // 首先第一步要指定迭代器映射@IterableMapping，指定单个对象转换，parentId能向下传递，使用@Context即可达到目的
        List<MenuDTO> menuDTOList = Arrays.asList(menuDTO1, menuDTO2);
        List<Menu> menuList = MenuDTO.convert(parentId, menuDTOList);
        Assert.assertEquals(parentId, menuList.get(0).getParentId());

    }

}
