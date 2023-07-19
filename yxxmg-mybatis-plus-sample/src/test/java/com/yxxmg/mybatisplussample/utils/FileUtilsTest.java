package com.yxxmg.mybatisplussample.utils;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : commons-io 文件测试
 * @since : 2022/11/24
 */
@RunWith(JUnit4.class)
public class FileUtilsTest {
    /**
     * 字符串写入文件
     *
     * @throws IOException 异常
     */
    @Test
    @Ignore
    public void test() throws IOException {
        String writeStr = "Hello Word";
        // 设置文件编码
        // writeStringToFile(File file, String data, String encoding)
        FileUtils.writeStringToFile(new File("d:/test/strFile.txt"), writeStr, StandardCharsets.UTF_8.name());
    }

    /**
     * 读取文件内容转byte array
     */
    @SneakyThrows
    @Ignore
    @Test
    public void test2() {
        byte[] bytes = FileUtils.readFileToByteArray(new File("d:/test/strFile.txt"));
        System.out.println(Arrays.toString(bytes));
    }

    /**
     * byte array 写到文件
     */
    @Test
    @Ignore
    public void test3() {
        // FileUtils.writeByteArrayToFile();
    }

    /**
     * 按行读取文件成列表
     */
    @SneakyThrows
    @Test
    @Ignore
    public void test4() {
        List<?> list = FileUtils.readLines(new File("d:/test/strFile.txt"));
        list.forEach(System.out::println);
    }

    @SneakyThrows
    @Test
    @Ignore
    public void test5() {
        // 读取文件内容
        // FileUtils.readFileToString()
        String s = FileUtils.readFileToString(new File("d:/test/strFile.txt"));
        System.out.println(s);
    }

    @SneakyThrows
    @Test
    @Ignore
    public void copy() {
        // FileUtils.copyDirectory();
        // FileUtils.copyFile(File srcFile, File destFile, boolean preserveFileDate)
        // FileUtils.copyURLToFile();
        // 递归删除文件夹下全部文件
        FileUtils.cleanDirectory(new File("d:/test"));
    }

}
