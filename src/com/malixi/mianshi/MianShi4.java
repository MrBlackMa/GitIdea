package com.malixi.mianshi;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 使用CountDownLatch 门闩
 *
 */
public class MianShi4 {
    List list=new LinkedList<>();



    public synchronized void  add(Object object){
        list.add(object);
    }


    public synchronized int size(){
        return list.size();
    }

    public static void main(String[] args) throws InterruptedException {
        MianShi4 m4=new MianShi4();
        CountDownLatch ct=new CountDownLatch(1);
        CountDownLatch ct2=new CountDownLatch(1);

        Thread t1=new Thread(()->{
            System.out.println("t1 start");
            for(int i=0;i<10;i++){
                 m4.add(new Object());
                 System.out.println(i);
                 if(m4.size()==5){
                     ct.countDown();
                     try {
                         ct2.await();
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
            }
            System.out.println("t1 end");
        },"t1");

        Thread t2=new Thread(()->{
            System.out.println("t2 start");
            try {
                ct.await();
                System.out.println(m4.size());
                ct2.countDown();  
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2 end");
        },"t2");


        t2.start();
        Thread.sleep(10);
        t1.start();

    }

}
