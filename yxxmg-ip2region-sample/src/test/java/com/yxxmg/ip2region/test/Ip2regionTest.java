package com.yxxmg.ip2region.test;

import java.io.InputStream;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.lionsoul.ip2region.xdb.Searcher;

import lombok.SneakyThrows;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 解析外网请求ip
 * @since : 2023/11/9
 */
@RunWith(JUnit4.class)
public class Ip2regionTest {
    @SneakyThrows
    @Test
    public void test() {
        String ip = "192.168.3.1";
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("ip2region.xdb");
        byte[] byteArray = IOUtils.toByteArray(resourceAsStream);
        Searcher searcher = Searcher.newWithBuffer(byteArray);
        String search = searcher.search(ip);
        System.out.println(search);
        System.out.println(Arrays.asList(search.split("\\|")));
        String search1 = searcher.search("180.101.50.188");
        System.out.println(Arrays.asList(search1.split("\\|")));
    }
}
