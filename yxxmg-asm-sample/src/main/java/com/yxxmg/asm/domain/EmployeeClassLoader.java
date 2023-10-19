package com.yxxmg.asm.domain;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 自定义类加载器
 * @since : 2023/10/11
 */
public class EmployeeClassLoader extends ClassLoader {
    public Class defineClassFromClassFile(String clazzName, byte[] classFile) {
        return defineClass(clazzName, classFile, 0, classFile.length);
    }

    public Class<?> defineClassForName(String name, byte[] data) {
        return this.defineClass(name, data, 0, data.length);
    }
}
