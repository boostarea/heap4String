package com.example.demo.ThreadTest;

/**
 * @description: 继承Thread类测试
 * @author: chenr
 * @date: 2017/8/16
 */
public class ExtendsThreadTest {
    public static void main(String[] args) {
        ThreadDemo t1 = new ThreadDemo("Thread-1");
        t1.start();
        ThreadDemo t2 = new ThreadDemo("Thread-2");
        t2.start();
    }
}

class ThreadDemo extends Thread{
    private Thread t;

    public ThreadDemo(String threadName) {
        super.setName(threadName);
        System.out.println("Construction " + threadName);
    }

    @Override
    public void run() {
        System.out.println("Running " + super.getName());
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Thread: " + super.getName() + "," + i);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + super.getName() + " interrupted.");
        }
        System.out.println("Thread: " + super.getName() + " exiting.");
    }

    public void start() {
        System.out.println("Starting " + super.getName());
        if (null == t) {
            t = new Thread(this, super.getName());
            t.start();
        }
    }

}
