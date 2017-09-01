package com.example.demo.ThreadTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @description: 实现Callable接口
 * @author: chenr
 * @date: 2017/8/16
 */
public class CallableThreadTest implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int i = 0;
        while(i < 100) {
            System.out.println(Thread.currentThread() + "," + i);
            i --;
        }
        return i;
    }

    public static void main(String[] args) {
        CallableThreadTest ct = new CallableThreadTest();
        FutureTask<Integer> ft = new FutureTask<Integer>(ct);
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " 的循环变量i的值" + i);
            if (20 == i) {
                new Thread(ft, "有返回值得线程").start();
            }
        }

        try {
            System.out.println("子线程返回值:" + ft.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
