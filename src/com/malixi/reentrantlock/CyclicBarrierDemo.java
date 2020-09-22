package com.malixi.reentrantlock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 栅栏
 */
public class CyclicBarrierDemo {
    static int count = 0;

    public static void main(String[] args) {
        CyclicBarrier c1 = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                System.out.println("人满了第"+count+++"次10");
            }
        });

        for (int i = 0; i < 11; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        c1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
