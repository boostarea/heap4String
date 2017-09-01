package com.example.demo.Proxy.StaticProxy;

import com.example.demo.Proxy.RealSubject;
import com.example.demo.Proxy.Subject;

/**
 * @description: 静态代理类（对抽象角色进行封装，并拦截方法的调用）
 * 1. 为每一个抽象角色（Subject）产生代理类，将导致类的数量剧增;
 *
 * @author: chenr
 * @date: 2017/8/21
 */
public class StaticProxy implements Subject {

    private Subject realSubject;

    public StaticProxy(Subject realSubject) {
        this.realSubject = realSubject;
    }

    public void before(String param) {
        if ("Test".equalsIgnoreCase(param)) {
            System.out.println("Illegal Param!");
        }
    }

    @Override
    public void doAction(String param) {
        this.before(param);
        realSubject.doAction(param);
    }

    /* outPutPrint
      -------------------------------------------
      param: vhrn
      Illegal Param
      param: Test
      -------------------------------------------
     */
    public static void main(String[] args) {
        Subject target = new RealSubject();
        Subject proxy = new StaticProxy(target);
        proxy.doAction("vhrn");
        proxy.doAction("Test");
    }
}
