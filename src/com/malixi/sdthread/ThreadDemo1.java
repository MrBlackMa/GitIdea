package com.malixi.sdthread;

/**
 * 两个线程交替输出第一种写法
 */
public class ThreadDemo1 {

    static class  A implements  Runnable{
        @Override
        public void run() {
            for(int i=1;i<11;i++){
                System.out.println(Thread.currentThread().getName()+" "+i);
            }
        }
    }

    static class B implements  Runnable{
        @Override
        public void run() {
            for(int i=10;i>0;i--){
                Thread.yield();
                System.out.println(Thread.currentThread().getName()+" "+i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        A a=new A();
        B b=new B();
        Thread thread=new Thread(a);
        Thread thread2=new Thread(b);
        thread.start();
        thread2.start();

    }
}
