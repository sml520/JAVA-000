package com.sxb.rocketmq.demo;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.file.Files;


/**
 * @Author 张元亮
 * @Date 2020/10/17 14:27
 * @Version 1.0
 */
public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) throws Exception {
        Class clazz = new HelloClassLoader().findClass("Hello");
        Method method = clazz.getMethod("hello");
        method.invoke(clazz.newInstance());
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classBytes = null;
        try {
            classBytes = Files.readAllBytes(new File("src/main/java/com/sxb/rocketmq/demo/Hello.xlass").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < classBytes.length; i++) {
            classBytes[i] = (byte) (255 - classBytes[i]);
        }
        Class<?> clazz = defineClass(name, classBytes, 0, classBytes.length);
        return clazz;
    }
}
