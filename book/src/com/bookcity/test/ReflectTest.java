package com.bookcity.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTest {
    public void login(){
        System.out.println("调用登陆业务");
    }

    public void register(){
        System.out.println("调用注册业务");
    }
    public void update(){
        System.out.println("调用修改业务");
    }

    public void find(){
        System.out.println("调用查询业务");
    }

    public static void main(String[] args) {
        String action="register";

        try {
            //获取目标方法，第一个参数是目标方法名称，第二个参数是参数类型
            Method method = ReflectTest.class.getDeclaredMethod(action);

            //调用目标方法，第一个参数是目标方法所在的类对象实例，第二个参数是调用的目标方法所需要的参数
            method.invoke(new ReflectTest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
