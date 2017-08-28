package com.example.classloading;

public class ConstClass {
    static {
        System.out.println("ConstClass");
    }

    public static final String STR = "test";
}
