package com.malixi.mianshi;

import java.util.LinkedList;
import java.util.List;


/**
 * 自己的想法 使用join
 */
public class MianShi3 {
  volatile   List list=new LinkedList<>();

    public synchronized void  add(Object object){
        list.add(object);
    }


    public synchronized int size(){
        return list.size();
    }

    public static void main(String[] args) {
        MianShi3 m3=new MianShi3();
        Thread t2=new Thread(()->{
            System.out.println("t2启动");
            try {
                // 10毫秒够我计算到数值五了
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("list的size:"+m3.size());
            System.out.println("t2结束");
        },"t2");



        Thread t1=new Thread(()->{
            System.out.println("t1启动");
                for(int i=0;i<10;i++){
                    m3.add(new Object());
                    System.out.println(i);
                    if(m3.size()==5){
                        try {
                            t2.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            System.out.println("t1结束");

        },"t1");
        t2.start();
        t1.start();

    }


}
