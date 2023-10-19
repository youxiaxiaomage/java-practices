package com.yxxmg.asm.test;

import java.io.FileOutputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.yxxmg.asm.domain.EmployeeClassLoader;
import com.yxxmg.asm.domain.EmployeeClassVisitor;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.ClassWriter;
import lombok.SneakyThrows;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : TODO
 * @since : 2023/10/18
 */
@RunWith(JUnit4.class)
public class AsmTest {

    @SneakyThrows
    @Test
    public void test() {

        // 1.定义ClassReader
        String sourceClassName = "com.yxxmg.asm.domain.Employee";
        ClassReader classReader = new ClassReader(sourceClassName);
        // 2.定义ClassWriter
        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
        // 3.定义ClassVisitor
        ClassVisitor classVisitor = new EmployeeClassVisitor(classWriter);

        // 定义classVisitor输入数据,
        // SKIP_DEBUG 如果设置了此标志，则这些属性既不会被解析也不会被访问
        // EXPAND_FRAMES 依次调用ClassVisitor 接口的各个方法
        classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES);

        // 将最终修改的字节码以byte数组形式返回
        byte[] bytes = classWriter.toByteArray();

        String targetClassName = "com.yxxmg.asm.domain.Employee$EnhancedByASM";
        Class<?> clazz = new EmployeeClassLoader().defineClassFromClassFile(targetClassName, bytes);
        System.out.println("clazz：" + clazz);

        // 通过文件流写入方式覆盖原先的内容，实现class文件的改写
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\data\\asm\\Employee$EnhancedByASM.class");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
