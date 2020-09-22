package com.malixi.reentrantlock;

import java.util.concurrent.Semaphore;

/**
 * 限流 限制线程数
 * 100个线程同时
 * Semaphore sp=new Semaphore(2)
 * 但是运行的只有 2个
 * 类似车道和收费站
 */
public class SemaphoreDemo {


    public static void main(String[] args) {
        Semaphore am=new Semaphore(1);

        new Thread(()->{
            try {
                am.acquire();

                System.out.println("T1 running...");
                Thread.sleep(200);
                System.out.println("T1 running...");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                am.release();
            }
        }).start();

        new Thread(()->{
            try {
               am.acquire();

                System.out.println("T2 running...");
                Thread.sleep(200);
                System.out.println("T2 running...");

                am.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
