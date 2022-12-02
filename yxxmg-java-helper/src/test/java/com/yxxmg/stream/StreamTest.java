package com.yxxmg.stream;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2022/12/2
 */
@RunWith(JUnit4.class)
public class StreamTest {
    @Test
    public void test() {
        List<User> userList =
            Stream
                .of(new User().setId("1").setUserName("张三").setPassword("123456").setAge(10),
                    new User().setId("2").setUserName("李四").setPassword("abc123").setAge(11),
                    new User().setId("3").setUserName("王五").setPassword("112233").setAge(15))
                .collect(Collectors.toList());
        // 过滤
        List<User> filterUserList = userList.stream().filter(user -> user.getAge() > 11).collect(Collectors.toList());
        System.out.println(MessageFormat.format("filterUserList:{0}", filterUserList));
        Assert.assertEquals(1, CollectionUtils.size(filterUserList));
        // 数据转换 如用户名集合
        List<String> userNameList = userList.stream().map(User::getUserName).collect(Collectors.toList());
        System.out.println(MessageFormat.format("userNameList:{0}", userNameList));
        Assert.assertTrue(userNameList.contains("张三"));
        // 转map
        Map<String, User> userMap = userList.stream().map(user -> Pair.of(user.getId(), user))
            .collect(Collectors.toMap(Pair::getKey, Pair::getValue, (v1, v2) -> v1));
        System.out.println(MessageFormat.format("userMap:{0}", userMap));
        Assert.assertTrue(MapUtils.isNotEmpty(userMap));
        Assert.assertTrue(userMap.containsKey("1"));
    }
}
