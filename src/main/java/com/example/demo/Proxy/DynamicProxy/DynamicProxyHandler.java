package com.example.demo.Proxy.DynamicProxy;

import com.example.demo.Proxy.RealSubject;
import com.example.demo.Proxy.Subject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description: JDK提供的动态代理
 * 1. 实现InvocationHandler的invoke方法，为整个代理入口;
 * 2. 通过Proxy的newProxyInstance创建代理对象;
 * 3. 若无法实现接口（InvocationHandler），则无法使用动态代理;
 *
 * @author: chenr
 * @date: 2017/8/21
 */
public class DynamicProxyHandler implements InvocationHandler {

    private Object target;

    public DynamicProxyHandler(Object target) {
        this.target = target;
    }

    private void before(String param) {
        if ("Test".equalsIgnoreCase(param)) {
            System.out.println("Illegal Param");
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.before((String) args[0]);
        return method.invoke(target, args);
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
        Subject proxy = (Subject) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new DynamicProxyHandler(target));
        proxy.doAction("chen");
        proxy.doAction("Test");
    }
}
