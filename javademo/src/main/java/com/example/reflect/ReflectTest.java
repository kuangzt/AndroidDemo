package com.example.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zengtao.kuang
 * @Description:
 * @date 2017/5/8 22:51
 * @copyright TCL-MIE
 */

public class ReflectTest {
    public static void main(String[] args){

        final AInterface aInterface = new AInterface(){

        };
        for(int i=0;i<10;i++){

            Object obj = Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, new InvocationHandler() {
                @Override
                public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                    return method.invoke(aInterface,objects);
                }
            });
            System.out.println(""+obj);
        }

    }
}
