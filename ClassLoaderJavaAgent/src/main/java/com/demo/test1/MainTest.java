package com.demo.test1;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String s) throws ClassNotFoundException {
                try {
                    if (s.equals("com.demo.test1.Hello")) {
                        byte[] classBytes = Files.readAllBytes(Paths.get("e:/JavaSpace/kingreatwill/java_study/ClassLoaderJavaAgent/src/main/resources/Hello.class"));
                        return defineClass(s, classBytes, 0, classBytes.length);
                    } else {
                        return super.loadClass(s);
                    }
                } catch (IOException e) {
                    throw new ClassNotFoundException(s);
                }
            }
        };

        Class  clazz = classLoader.loadClass("com.demo.test1.Hello");


        Object outside2 = clazz.newInstance();
        outside2.getClass().getMethod("say").invoke(outside2);


        Father outside = (Father) clazz.newInstance();
        outside.say();

        Hello inside = new Hello();
        inside.say();
    }
}
