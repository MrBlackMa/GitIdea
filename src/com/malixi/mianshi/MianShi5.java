package com.malixi.mianshi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class MianShi5 {
    List list=new ArrayList<>();

    public int  size(){
        return list.size();
    }

    public void add(Object object){
        list.add(object);
    }

    static Thread t1=null;
    static Thread t2=null;
    public static void main(String[] args) throws InterruptedException {
        MianShi5 ms=new MianShi5();

       t2=new Thread(()->{
             System.out.println("t2启动");
             LockSupport.park();
             System.out.println(ms.size());
             LockSupport.unpark(t1);
             System.out.println("t2结束");

        },"t2");
        t2.start();


         t1=new Thread(()->{
            System.out.println("t1启动");
            for(int i=0;i<10;i++){
                ms.add(new Object());
                System.out.println(i);
                if(ms.size()==5){
                    LockSupport.unpark(t2);
                    LockSupport.park(t2);
                }
            }
             System.out.println("t1结束");

        },"t1");
        t1.start();


    }

}
