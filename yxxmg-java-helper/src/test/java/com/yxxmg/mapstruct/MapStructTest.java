package com.yxxmg.mapstruct;

import com.yxxmg.mapstruct.convert.AddressMapper;
import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    public void test8() {
        // 默认的隐式转换 如实体类中都是基于接口BaseEnum的枚举类 转给前端是字符串类型
        // 主要是针对统一类型数据的转换
        User user1 = new User().setUserId("1").setUserName("张三").setGender(Gender.MALE).setStatus(Status.ENABLE);
        User user2 = new User().setUserId("2").setUserName("李四").setGender(Gender.MALE).setStatus(Status.DISABLE);
        User user3 = new User().setUserId("3").setUserName("韩梅梅").setGender(Gender.FEMALE).setStatus(Status.ENABLE);

        List<User> userList = Arrays.asList(user1, user2, user3);
        List<UserDTO> userDTOList = UserDTO.convert(userList);
        System.out.println(userDTOList);
        // [UserDTO(userId=1, userName=张三, gender=男, status=启用),
        // UserDTO(userId=2, userName=李四, gender=男, status=停用),
        // UserDTO(userId=3, userName=韩梅梅, gender=女, status=启用)]
        Assert.assertEquals("启用",
                Objects.requireNonNull(userDTOList.stream()
                                .filter(userDTO -> StringUtils.equals(userDTO.getUserName(), "张三")).findFirst().orElse(null))
                        .getStatus());
    }

    public void test9() {
        // MapStruct也可以根据多个参数进行映射
        Person person = new Person().setDescribe("描述");
        Address address = new Address().setHouseNo("门牌号");
        DeliveryAddressDto deliveryAddressDto =
                AddressMapper.MAPPER.personAndAddressToDeliveryAddressDto(person, address);
        Assert.assertEquals("描述", deliveryAddressDto.getDescription());
        Assert.assertEquals("门牌号", deliveryAddressDto.getHouseNumber());
    }

}
