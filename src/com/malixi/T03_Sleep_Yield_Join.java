package com.malixi;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 线程t1 线程t2 线程t3同时进行 当线程同时跑的时候 主线程的优先级大于t1 t2 t3
 * Systemout的输出为3个零
 *
 */
public class T03_Sleep_Yield_Join {
  //private  volatile
  long count=0;
  AtomicLong atomicLong=new AtomicLong(0);

    Object ob=new Object();
    //synchronized
    void m() {
      //synchronized (ob){
          for (long i = 0; i < 100000; i++)
           //   count++;
        atomicLong.getAndIncrement();
     // }
    }
    public static void main(String[] args) throws InterruptedException {
        T03_Sleep_Yield_Join t03_sleep_yield_join=new T03_Sleep_Yield_Join();
        //t03_sleep_yield_join.testJoin();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                    System.out.println(Thread.currentThread().getName());
                    t03_sleep_yield_join.m();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                    System.out.println(Thread.currentThread().getName());
                    t03_sleep_yield_join.m();
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                    System.out.println(Thread.currentThread().getName());
                    t03_sleep_yield_join.m();

            }
        });

        t1.start();
        //t1.join();
        t2.start();
        //t2.join();
        t3.start();
        //t3.join();
        System.out.println(t03_sleep_yield_join.count);
        for (int i=0;i<5;i++){
            System.out.println(Thread.currentThread().getName()+"main");
        }
        System.out.println(t03_sleep_yield_join.count);
        System.out.println(t03_sleep_yield_join.atomicLong);


//        t1.run();
//        System.out.println(t03_sleep_yield_join.count);
//        t2.run();
//        System.out.println(t03_sleep_yield_join.count);
//        t3.run();
//        System.out.println(t03_sleep_yield_join.count);

    }
}