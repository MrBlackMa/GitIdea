package com.malixi;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 线程t1 线程t2 线程t3同时进行 当线程同时跑的时候 主线程的优先级大于t1 t2 t3
 * Systemout的输出为3个零 或者一个小于i循环的数目
 * 当加入join方法的时候,线程合并成了一个单线线程，当前线程执行完 才执行下一个线程
 * 而当不加join  三个线程同时执行,拿到数字会产生并发数据不对问题
 * 所以 同时执行需要加上锁数据 那么数据一定就对了
 * CPU执行没有synchronized的数据的时候,  并行拿到数据 产生脏数据
 * CPU执行有synchronized的数据 就是线程全部准备就绪,等待数据的释放,然后争抢锁资源拿到进行执行逻辑
 */
public class T03_Sleep_Yield_Join {
    //private  volatile
    long count = 0;
    AtomicLong atomicLong = new AtomicLong(0);

    Object ob = new Object();

    synchronized void m() {
        //synchronized (ob){
        for (long i = 0; i < 100000; i++)
            System.out.println(Thread.currentThread().getName()+"执行++"+count++);
              //atomicLong.getAndIncrement();
        // }
    }

    public static void main(String[] args) throws InterruptedException {
        T03_Sleep_Yield_Join t03_sleep_yield_join = new T03_Sleep_Yield_Join();
        //t03_sleep_yield_join.testJoin();
//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//                t03_sleep_yield_join.m();
//            }
//        });
        Thread t1=new Thread(()-> {
                System.out.println(Thread.currentThread().getName());
                t03_sleep_yield_join.m();
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
        Thread.sleep(1500);
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