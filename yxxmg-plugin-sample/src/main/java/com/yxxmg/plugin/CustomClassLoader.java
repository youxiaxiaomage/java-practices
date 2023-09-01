package com.yxxmg.plugin;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.util.List;
import java.util.Objects;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 自定义类加载器
 * @since : 2023/8/11
 */
@Slf4j
public class CustomClassLoader extends URLClassLoader {
    private final List<JarURLConnection> jarURLConnectionList = Lists.newArrayList();

    public CustomClassLoader() {
        super(new URL[0], null);
    }

    @Override
    public Class<?> loadClass(String clazzName) throws ClassNotFoundException {
        Class<?> clazz = findLoadedClass(clazzName);
        if (Objects.isNull(clazz)) {
            try {
                clazz = findClass(clazzName);
            } catch (ClassNotFoundException e) {
                return super.loadClass(clazzName);
            }
        }
        return clazz;
    }

    public void loadJar(String jarPath) {
        addURL(new File(jarPath));
    }

    private void addURL(File file) {
        try {
            super.addURL(new URL("file", null, file.getCanonicalPath()));
        } catch (IOException e) {
            log.warn("add jar url failed with ex:", e);
        }
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> clazz = findLoadedClass(name);
        if (Objects.isNull(clazz)) {
            try {
                clazz = findClass(name);
            } catch (ClassNotFoundException e) {
                return super.loadClass(name, resolve);
            }
        } else {
            resolveClass(clazz);
        }
        return clazz;
    }

    @Override
    protected void addURL(URL url) {
        try {
            URLConnection urlConnection = url.openConnection();
            if (urlConnection instanceof JarURLConnection) {
                urlConnection.setUseCaches(true);
                jarURLConnectionList.add((JarURLConnection)urlConnection);
            }
        } catch (IOException e) {
            log.warn("add jar url failed with ex:", e);
        }
        super.addURL(url);
    }

    @Override
    public void close() throws IOException {
        for (JarURLConnection urlConnection : jarURLConnectionList) {
            urlConnection.getJarFile().close();
        }
        jarURLConnectionList.clear();
        super.close();
    }

    public void closeCustomClassLoaderWithRetry() {
        Exception ex = null;
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(500L);
                close();
            } catch (InterruptedException | IOException e) {
                ex = e;
            }
        }
        throw new IllegalStateException(ex);
    }
}
