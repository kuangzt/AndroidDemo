package com.example.classloading;

public class SuperClass {
    static {
        System.out.println("SuperClass init!");
    }
    public static int i = 3;
}
