package com.malixi.sdthread;

public class TestJoin {
    public static void main(String[] args) throws InterruptedException {
        MyRun myRun=new MyRun();
        Thread t1=new Thread(myRun);
        t1.start();
        for(int i=0;i<5;i++){
           // t1.join();
            System.out.println(t1.isAlive());
            System.out.println(Thread.currentThread().getName()+"--"+i);
        }

    }

}
