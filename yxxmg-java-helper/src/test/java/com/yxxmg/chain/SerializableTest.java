package com.yxxmg.chain;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/9/16
 */
@RunWith(JUnit4.class)
public class SerializableTest {
    @Test
    public void test() throws IOException, ClassNotFoundException {
        User user = new User();
        user.setUserId("123456");
        user.setUserName("张三");
        user.setAge(15);
        File file = new File("1.txt");
        ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(file.toPath()));
        oos.writeObject(user);

        ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(file.toPath()));
        User user2 = (User) ois.readObject();
        Assert.equals("123456", user2.getUserId());
        Assert.isNull(user2.getAge());

    }

    @Test
    public void test2() {
        Teacher teacher = new Teacher();
        teacher.setTeacherId("1234567");
        teacher.setTeacherName("teacher");
        String jsonString = JSONObject.toJSONString(teacher);

        Teacher teacher1 = JSONObject.parseObject(jsonString, Teacher.class);
        Assert.equals("1234567", teacher1.getTeacherId());
    }
}
