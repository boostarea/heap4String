package com.example.demo.ThreadTest;

/**
 * @description:
 * @author: chenr
 * @date: 2017/8/16
 */
class RunnableDemo implements Runnable {
    private Thread t;
    private String threadName;

    public RunnableDemo(String threadName) {
        this.threadName = threadName;
        System.out.println("Construction " + threadName);
    }

    @Override
    public void run() {
        System.out.println("Running " + threadName);

        try {
            for (int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + "," + i);
                Thread.sleep(50);
                System.out.println("Sleep");
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");
        }
        System.out.println("Thread: " + threadName + " exiting.");
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (null == t) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}

public class RunnableTest {
    public static void main(String[] args) {
        RunnableDemo demo1 = new RunnableDemo("Thread-1");
        demo1.start();
        RunnableDemo demo2 = new RunnableDemo("Thread-2");
        demo2.start();
    }
}