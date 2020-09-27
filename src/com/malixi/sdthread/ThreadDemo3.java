package com.malixi.sdthread;

import com.malixi.TicketThread;

/**
 * 创建线程 输出1-100之间的偶数
 */

public class ThreadDemo3 implements Runnable{
    @Override
    public void run() {
         for(int i=0;i<=100;i++){
             if(i%2==0){
                 System.out.println(i);
             }
         }
    }

    public static void main(String[] args) {
        ThreadDemo3 t3=new ThreadDemo3();
        Thread thread=new Thread(t3);
        thread.start();
    }
}
