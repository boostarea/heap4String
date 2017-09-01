package com.example.demo.ThreadTest;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: chenr
 * @date: 2017/8/17
 */
public class JoinTest implements Runnable {
    private String name;

    public JoinTest(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("%s begins: %s\n",name ,new Date());
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s has finished: %s\n", name, new Date());
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new JoinTest("One"));
        Thread thread2 = new Thread(new JoinTest("Two"));
        thread1.start();
        thread2.start();

        try {
            thread2.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread is finished!");
    }
}
