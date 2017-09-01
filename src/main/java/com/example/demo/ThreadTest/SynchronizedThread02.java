package com.example.demo.ThreadTest;

/**
 * @description:
 * @author: chenr
 * @date: 2017/8/17
 */

public class SynchronizedThread02
{
    public static void main(String[] args)
    {
        Example example = new Example();

        Thread t1 = new Thread1(example);
        Thread t2 = new Thread1(example);

        t1.start();
        t2.start();
    }

}

class Example
{
    public synchronized void execute()
    {
        for (int i = 0; i < 10; ++i)
        {
            try
            {
                Thread.sleep(500);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("Hello: " + i);
        }
    }

}

class Thread1 extends Thread
{
    private Example example;

    public Thread1(Example example)
    {
        this.example = example;
    }

    @Override
    public void run()
    {
        example.execute();
    }

}