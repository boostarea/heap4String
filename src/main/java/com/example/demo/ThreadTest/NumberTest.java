package com.example.demo.ThreadTest;

/**
 * @description:
 * @author: chenr
 * @date: 2017/8/17
 */
 class NumberHolder {
    private int number;

    public synchronized void increase() {
        // 在被唤醒之后仍然进行条件判断
        while (0 != number) {
        // if (0 != number) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 能执行到这里说明已经被唤醒
        // 并且number为0
        number++;
        System.out.println(number);

        // 通知在等待的线程
        notifyAll();
    }

    public synchronized void decrease()
    {
        while (0 == number)
        // if (0 == number)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        }

        // 能执行到这里说明已经被唤醒
        // 并且number不为0
        number--;
        System.out.println(number);
        notifyAll();
    }

}


class IncreaseThread extends Thread
{
   private NumberHolder numberHolder;

   public IncreaseThread(NumberHolder numberHolder)
   {
       this.numberHolder = numberHolder;
   }

   @Override
   public void run()
   {
       for (int i = 0; i < 20; ++i)
       {
           // 进行一定的延时
           try
           {
               Thread.sleep((long) Math.random() * 1000);
           }
           catch (InterruptedException e)
           {
               e.printStackTrace();
           }

           // 进行增加操作
           numberHolder.increase();
       }
   }

}


class DecreaseThread extends Thread
{
   private NumberHolder numberHolder;

   public DecreaseThread(NumberHolder numberHolder)
   {
       this.numberHolder = numberHolder;
   }

   @Override
   public void run()
   {
       for (int i = 0; i < 20; ++i)
       {
           // 进行一定的延时
           try
           {
               Thread.sleep((long) Math.random() * 1000);
           }
           catch (InterruptedException e)
           {
               e.printStackTrace();
           }

           // 进行减少操作
           numberHolder.decrease();
       }
   }

}


public class NumberTest
{
    public static void main(String[] args)
    {
        NumberHolder numberHolder = new NumberHolder();

        Thread t1 = new IncreaseThread(numberHolder);
        Thread t2 = new DecreaseThread(numberHolder);

        Thread t3 = new IncreaseThread(numberHolder);
        Thread t4 = new DecreaseThread(numberHolder);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

}