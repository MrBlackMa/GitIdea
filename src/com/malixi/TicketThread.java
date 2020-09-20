package com.malixi;

public class TicketThread extends  Thread{
   private  int ticket=5;
   Object ob=new Object();
   int j=0;

    @Override
    public  void run() {
        for(int i=0;i<100;i++){
//            synchronized (ob){
//                if(ticket>0){
//                    System.out.println(Thread.currentThread().getName()+"正在出售第"+(ticket--)+"张票");
//                }
//            }
            System.out.println(Thread.currentThread().getName()+" "+j++);
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
    }
}
