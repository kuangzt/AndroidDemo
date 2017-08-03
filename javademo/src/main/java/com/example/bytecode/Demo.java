package com.example.bytecode;

/**
 * javac生成class文件，然后用javap -verbose命令查看
 */
public final class Demo {

    private boolean flag = false;

    public boolean getFlag() {
        return flag;
    }
}
