package com.example.ploymorphic;

public class StaticDispatch {
    static abstract class Human {

    }

    static class Man extends Human {

    }

    static class Woman extends Human {

    }

    public static void sayHello(Human human) {
        System.out.println("Human hello!!");
    }

    public static void sayHello(Man man) {
        System.out.println("Man hello!!");
    }

    public static void sayHello(Woman woman) {
        System.out.println("Woman hello!!");
    }

    public static void main(String[] args) {
        Human human = new Man();
        sayHello(human);
        human = new Woman();
        sayHello(human);
    }
}
