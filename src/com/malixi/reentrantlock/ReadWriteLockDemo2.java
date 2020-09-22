package com.malixi.reentrantlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读锁为共享锁
 * 写锁为排他锁
 */
public class ReadWriteLockDemo2 {
    static Lock lock = new ReentrantLock();
    public static ReadWriteLock rtrwLock = new ReentrantReadWriteLock();

    static Lock readlock = rtrwLock.readLock();
    static Lock writeLock = rtrwLock.writeLock();

    private static int value;

    public static void readLock(Lock lock) {
        lock.lock();
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "读操作");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void writeLock(int v, Lock lock) {
        lock.lock();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        value = v;
        System.out.println(Thread.currentThread().getName() + "写操作");


    }


    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    readLock(readlock);
                }
            }).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    writeLock(new Random().nextInt(), writeLock);
                }
            }).start();
        }
    }
}
