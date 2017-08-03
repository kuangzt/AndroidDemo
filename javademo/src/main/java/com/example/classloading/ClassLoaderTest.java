package com.example.classloading;

import java.io.FileInputStream;
import java.io.InputStream;

public class ClassLoaderTest {

    public static void main(String[] args) {
        ClassLoader loader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf(".")+1)+".class";
                InputStream is = getClass().getResourceAsStream(fileName);
                if (is == null) {
                    return super.loadClass(name);
                }
                try {
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    defineClass(name,b,0,b.length);
                } catch (Exception e) {

                }


                return super.loadClass(name);
            }
        };
        try {
            Class clazz = loader.loadClass("com.example.classloading.ClassLoaderTest");
            Object obj = clazz.newInstance();
            System.out.println(obj instanceof ClassLoaderTest);
        } catch (Exception e) {

        }


    }
}
