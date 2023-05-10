package com.yxxmg.gof.test.relation.command;

import com.yxxmg.gof.relation.command.SpringProcessEngineConfiguration;
import com.yxxmg.gof.relation.command.TaskServiceImpl;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 命令模式测试用例
 * @since : 2023/5/10
 */
@RunWith(JUnit4.class)
public class CommandTest extends TestCase {
    @Test
    public void test() {
        SpringProcessEngineConfiguration springProcessEngineConfiguration = new SpringProcessEngineConfiguration();
        springProcessEngineConfiguration.init();
    }
}
