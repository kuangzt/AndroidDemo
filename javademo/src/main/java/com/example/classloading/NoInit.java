package com.example.classloading;

public class NoInit {

    public static void main(String[] args) {
        //System.out.println(SubClass.i);//被动引用1
        //SuperClass[] superClasses = new SuperClass[10];//被动引用2
        //SubClass[] subClasses = new SubClass[10];//被动引用2
        System.out.println(ConstClass.STR);//被动引用3
    }
}
