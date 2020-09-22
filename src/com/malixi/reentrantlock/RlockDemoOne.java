package com.malixi.reentrantlock;

public class RlockDemoOne {
     synchronized void m() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            if (i % 2==0) {
//                m2();
//            }
        }
    }

     synchronized void m2() {
        System.out.println("您好");
    }

    public static void main(String[] args) throws InterruptedException {
        RlockDemoOne rlockDemoOne=new RlockDemoOne();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                rlockDemoOne.m();
            }
        });
        t1.start();
        Thread.sleep(1000);
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
               rlockDemoOne.m2();
            }
        });
        t2.start();
    }

}
