package com.malixi;

public class TicketRunnable implements Runnable {
    private int ticket = 10000;
    Object ob = new Object();

    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            synchronized (ob) {
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "正在出售第" + (ticket--) + "张票");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TicketRunnable t1 = new TicketRunnable();
        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t1);
        Thread thread3 = new Thread(t1);
        Thread thread4 = new Thread(t1);
        Thread thread5 = new Thread(t1);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();


    }
}
