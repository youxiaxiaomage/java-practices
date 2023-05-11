package com.yxxmg.jsqlparser;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : TODO
 * @since : 2023/5/11
 */
@RunWith(JUnit4.class)
public class JsqlparserTest {
    @Test
    public void test() throws JSQLParserException {
        // 使用工具类把SQL转换为Select对象
        Select select = (Select)CCJSqlParserUtil.parse("SELECT username,age,sex FROM user");
        SelectBody selectBody = select.getSelectBody();
        System.err.println(selectBody);
    }
}
