package com.yxxmg.plugin.jar;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Permission;
import java.util.jar.JarFile;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/5
 */
public abstract class AbstractJarFile extends JarFile {
    public AbstractJarFile(File file) throws IOException {
        super(file);
    }

    /**
     * 获取URL
     * 
     * @return URL
     * @throws MalformedURLException
     */
    abstract URL getUrl() throws MalformedURLException;

    /**
     * 获取输入流
     * 
     * @return InputStream
     * @throws IOException
     */
    abstract InputStream getInputStream() throws IOException;

    /**
     * 获取JarFileType
     * 
     * @return JarFileType
     */
    abstract JarFileType getJarFileType();

    /**
     * 获取权限
     * 
     * @return Permission
     */
    abstract Permission getPermission();

    /**
     * JarFileType枚举
     */
    enum JarFileType {
        DIRECT, NESTED_DIRECTORY, NESTED_JAR
    }
}