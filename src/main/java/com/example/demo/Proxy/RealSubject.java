package com.example.demo.Proxy;

/**
 * @description: 目标角色
 * @author: chenr
 * @date: 2017/8/21
 */
public class RealSubject implements Subject {
    @Override
    public void doAction(String param) {
        System.out.println("param: " + param);
    }
}
