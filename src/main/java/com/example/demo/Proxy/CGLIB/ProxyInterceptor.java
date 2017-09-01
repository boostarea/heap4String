package com.example.demo.Proxy.CGLIB;

import com.example.demo.Proxy.RealSubject;
import com.example.demo.Proxy.Subject;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description: 通过字节码增强（CGLIB）实现动态代理
 * 1. 通过动态生成目标类(RealSubject)的子类，故目标类方法不能被final修饰;
 *
 * @author: chenr
 * @date: 2017/8/21
 */
public class ProxyInterceptor implements MethodInterceptor {

    private void before(String param) {
        if ("Test".equalsIgnoreCase(param)) {
            System.out.println("Illegal Param");
        }
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        this.before((String) objects[0]);
        return methodProxy.invokeSuper(o, objects);
    }

    /* outPutPrint
      -------------------------------------------
      param: vhrn
      Illegal Param
      param: Test
      -------------------------------------------
     */
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer(); // 通过字节码增强器
        enhancer.setSuperclass(RealSubject.class);
        enhancer.setCallback(new ProxyInterceptor());
        Subject proxy = (Subject) enhancer.create();
        proxy.doAction("chen");
        proxy.doAction("Test");
    }
}
