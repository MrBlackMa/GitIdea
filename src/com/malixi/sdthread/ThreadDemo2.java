package com.malixi.sdthread;

/**
 * 两种线程交替输出方法2
 */
public class ThreadDemo2 {


    public static void main(String[] args) {
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        t1.start();
    }


}
