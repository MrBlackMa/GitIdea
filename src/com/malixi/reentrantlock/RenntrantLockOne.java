package com.malixi.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentranLock
 * 可重入锁 等于synchronized锁
 */
public class RenntrantLockOne {
    Lock rlock=new ReentrantLock();

    void m1(){
        try{
          //  rlock.lock();
            for(int i=0;i<10;i++){
                Thread.sleep(500);
                System.out.println(i);
            }
        }catch (Exception e){
           // e.printStackTrace();
        }finally {
           // rlock.unlock();
        }
    }

    void m2(){
        try{
            rlock.lock();
            System.out.println("m2...");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rlock.unlock();
        }
    }

    public static void main(String[] args) {
        RenntrantLockOne renntrantLockOne=new RenntrantLockOne();
        new Thread(renntrantLockOne::m1).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(renntrantLockOne::m2).start();

    }
}
