package com.malixi.mianshi;

import java.util.LinkedList;

/**
 * 必会
 * 面试题 写一个固定容量同步容器，拥有put和get方法
 * 以及getCount方法
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * wait 释放锁 notify不能释放锁
 * 使用wait和notify/notifyAll来实现
 *  * 有个问题肯定想不通 就是：当生产者执行了生产 list.add 然后  this.notifyAll();// 通知消费者线
 *  * 消费者这个时候会判断里面不是零 为什么不消费呢？
 *  * 解答：因为这个时候 数据还是被锁着在 notify是不能释放锁的  在生产者里面第一行 一直不满足wait条件
 *  * 只有等放满10个 触发wait 才释放锁,然后等待执行的消费者才执行 发现不等于0 锁起来 开始消费 等消费完毕
 *  * 然后wait 阻塞住
 *
 */
public class ProductsAndConsumers<T> {

    final private LinkedList<T> list = new LinkedList<>();

    final private int MAX = 10;

    private int count=0;

    //生产者方法
    public synchronized void put(T t){
        // 用while  而不用if判断 是因为 当线程醒了 会继续判断是不是别人的线程把他丢满了 如果还是满的 则继续wait
        // 如果用 if则不会回来判断 别人的线程会不会丢满 而会继续执行下去 那么就会出现问题
        while(list.size()==MAX){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        count++;
        this.notifyAll();
    }

    //消费者方法
    public synchronized T getT(){
        T t=null;
        while (list.size()==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t=list.removeFirst();
        count--;
        this.notifyAll();
        return t;
    }

    public int getCount(){
        return  count;
    }

    public static void main(String[] args) {
        ProductsAndConsumers pac=new ProductsAndConsumers();
        // 两个生产者线程
        for(int i=0;i<2;i++){
            new Thread(()->{
                for(int j=0;j<12;j++){
                    pac.put(Thread.currentThread().getName()+" "+j);
                }
            },"product"+i).start();
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(pac.getCount());

        //10个消费者线程
        for(int i=0;i<12;i++){
            new Thread(()->{
                for(int j=0;j<2;j++){
                    System.out.println(pac.getT());
                }
            },"consumers"+i).start();
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(pac.getCount());




    }


}
