package com.malixi;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 一个对象 多个线程
 * AtomicInteger 只能保证数据的原子性 不能保证数据的有序性
 */
public class TicketRunnable implements Runnable {
    private int ticket = 10;
    volatile AtomicInteger at=new AtomicInteger(99);

    // 限流：最多3个线程执行 就算是100个线程也需要等待
    Semaphore s = new Semaphore(3);
    // 满两个线程就发车
    CyclicBarrier c = new CyclicBarrier(2, new Runnable() {
        @Override
        public void run() {
            System.out.println("人满了");
        }
    });

    @Override
    public void run() {
        try {
                s.acquire();
//            try {
//                c.await();
//            } catch (BrokenBarrierException e) {
//                e.printStackTrace();
//            }
            for (int i = 0; i < 100; i++) {
                Thread.sleep(10);
              // synchronized (this) {
                    //if (ticket > 0) {
                if (at.getAndAdd(0) > 0) {
                        System.out.println(Thread.currentThread().getName() + "正在出售第" + at.getAndDecrement() + "张票");
                    }
               // }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            s.release();
        }

    }


    public static void main(String[] args) throws InterruptedException {
        TicketRunnable t1 = new TicketRunnable();
        Thread thread0 = new Thread(t1);
        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t1);
        Thread thread3 = new Thread(t1);
        Thread thread4 = new Thread(t1);
        Thread thread5 = new Thread(t1);
        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        Thread.sleep(1000);
        System.out.println(t1.at.get());
    }
}
