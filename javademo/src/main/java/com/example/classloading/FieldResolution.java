package com.example.classloading;

public class FieldResolution {
    public interface Interface0 {
        int A = 0;
    }

    public interface Interface1 extends Interface0 {
        int A = 1;
    }

    public interface Interface2 {
        int A = 2;
    }

    public static class Parent implements Interface1 {
        public static int A = 3;
    }

    public static class Sub extends Parent implements Interface2 {
        public static int A = 4;//注释掉这行代码，将会报错提示 Reference to 'A' is Ambiguous,Both 'Parent.A' and 'Interface2.A' match.
    }

    public static void main(String[] args) {
        System.out.println(Sub.A);
    }
}
