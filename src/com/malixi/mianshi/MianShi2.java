package com.malixi.mianshi;

import java.util.LinkedList;
import java.util.List;

/**
 * 使用wait  wait 是可以释放锁的
 * notify唤醒线程 notify 不能释放锁
 */
public class MianShi2 {
    List list=new LinkedList<>();

    public void  add(Object object){
        list.add(object);
    }



    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        MianShi2 m2=new MianShi2();
        final  Object object=new Object();
        Thread t2=new Thread(()->{
            System.out.println("t2启动");
            synchronized (m2){
                try {
                    //t2暂停 释放锁 t2监听着-->第一步
                  m2.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("list的size:"+m2.size());
                System.out.println("t2结束");
                //没有这句话 t1则不继续执行 通知t1继续执行 --第四步
                  m2.notify();
            }

        },"t2");



        Thread t1=new Thread(()->{
            System.out.println("t1启动");
            synchronized (m2){
                for(int i=0;i<10;i++){
                    m2.add(new Object());
                    System.out.println(i);
                   if(m2.size()==5){
                       // 通知t2 继续进行 -->第二步
                       m2.notify();
                       try {
                           // t1暂停 并且释放锁 -->第三步
                           m2.wait();
                           System.out.println("t1还没结束");
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
                }
            }
            //-->第五步
            System.out.println("t1结束");

        },"t1");
        t2.start();
        t1.start();

    }


}
