package com.yxxmg.nlp2cron.test;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import cn.langpy.nlp2cron.CrondUtil;
import cn.langpy.nlp2cron.core.CrondModel;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/9/6
 */
@RunWith(JUnit4.class)
public class Nlp2cronTest {
    @Ignore("xxx")
    @Test
    public void test() {
        CrondModel.init("D:/keras-model-v2");
        String test1 = "明早八点";
        String test2 = "每天晚上7点开始";
        String test3 = "每15分钟一次";
        String test4 = "每2小时一次";
        String test5 = "每天晚上7点开始";
        String test6 = "每天早上7点开始";
        String test7 = "上午一点执行";
        String test8 = "明天早上8点";
        String cron1 = CrondUtil.toCron(test1);
        System.out.println(cron1);
        String cron2 = CrondUtil.toCron(test2);
        System.out.println(cron2);
        String cron3 = CrondUtil.toCron(test3);
        System.out.println(cron3);
        String cron4 = CrondUtil.toCron(test4);
        System.out.println(cron4);
        String cron5 = CrondUtil.toCron(test5);
        System.out.println(cron5);
        String cron6 = CrondUtil.toCron(test6);
        System.out.println(cron6);
        String cron7 = CrondUtil.toCron(test7);
        System.out.println(cron7);
        String cron8 = CrondUtil.toDate(test8);
        System.out.println(cron8);
        String cron9 = CrondUtil.toDateTime(test8);
        System.out.println(cron9);
        String cron10 = CrondUtil.toTime(test8);
        System.out.println(cron10);
        String test9 = "每隔30分钟一次";
        String cron11 = CrondUtil.toCron(test9);
        System.out.println(cron11);
        /*使用完关闭 如果在web中需要重复使用则不需要关闭*/
        CrondModel.close();
        // 明早八点 转为cron表达式：0 0 8 7 9 ? 2023
        // 每天晚上7点开始 转为cron表达式：0 0 19 * * ? *
        // 每15分钟一次 转为cron表达式：0 0/15 * * * ? *
        // 每2小时一次 转为cron表达式：0 0 0/2 * * ? *
        // 每天晚上7点开始 转为cron表达式：0 0 19 * * ? *
        // 每天早上7点开始 转为cron表达式：0 0 7 * * ? *
        // 上午一点执行 转为cron表达式：0 0 1 * * ? *
        // 明天早上八点 转为date表达式：2023-09-07
        // 明天早上八点 转为datetime表达式：2023-09-07 08:00:00
        // 明天早上八点 转为time表达式：08:00:00
    }
}
