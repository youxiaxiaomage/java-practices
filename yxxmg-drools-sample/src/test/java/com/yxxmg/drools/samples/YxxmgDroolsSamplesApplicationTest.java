package com.yxxmg.drools.samples;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yxxmg.drools.samples.domain.Person;

@SpringBootTest
@RunWith(SpringRunner.class)
public class YxxmgDroolsSamplesApplicationTest {
    @Resource
    private KieSession kieSession;

    @Resource
    private KieBase kieBase;

    @Test
    public void contextLoad() {}

    @AfterEach
    public void runDispose() {
        kieSession.destroy();
    }

    @Test
    public void personTest() {
        Person person = new Person();
        person.setName("张三");
        person.setSex(1);
        person.setDrlType("person");
        kieSession.insert(person);// 插入
        kieSession.fireAllRules();// 执行规则
    }

    @Test
    public void playTest() {

        Person person = new Person();
        person.setName("张3");
        person.setSex(1);
        person.setAge(20);

        kieSession.insert(person);// 插入
        kieSession.fireAllRules();// 执行规则

        System.out.println(person.isEnablePlay());
    }

    @Test
    public void playUnableTest() {

        Person person = new Person();
        person.setName("李4");
        person.setSex(1);
        person.setAge(16);
        person.setDrlType("person");

        kieSession.insert(person);// 插
        kieSession.fireAllRules();// 执行规则
        System.out.println(person.isEnablePlay());
    }

}