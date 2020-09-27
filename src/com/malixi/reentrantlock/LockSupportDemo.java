package com.malixi.reentrantlock;

import java.util.concurrent.locks.LockSupport;

/**
 * 支持性锁
 * 唤醒，阻塞线程类
 */
public class LockSupportDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if(i==5){
                    //阻塞当前线程
                    LockSupport.park();
                }
            }
        });
        t1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 唤醒当前线程
        LockSupport.unpark(t1);
    }
}
