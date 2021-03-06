package com.malixi;

/**
 * 多个对象
 * 多个线程
 */
public  class TicketThread extends  Thread{
    @Override
    public  void run() {
        for(int i=0;i<100000;i++){
            synchronized (IntegerEum.ob){
               // if(IntegerEum.getIntegerEum().ticket>0){
                    if(IntegerEum.at.getAndAdd(0)>0){
                    System.out.println(Thread.currentThread().getName()+"正在出售第"+(IntegerEum.at.getAndDecrement())+"张票");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TicketThread t1=new TicketThread();
        TicketThread t2=new TicketThread();
        TicketThread t3=new TicketThread();
        TicketThread t4=new TicketThread();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        Thread.sleep(5000);
        System.out.println(IntegerEum.at.get());
    }
}
