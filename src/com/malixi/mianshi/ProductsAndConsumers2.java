package com.malixi.mianshi;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有个问题肯定想不通 就是：当生产者执行了生产 list.add 然后  consumers.signalAll();// 通知消费者线
 *
 * 消费者这个时候会判断里面不是零 为什么不消费呢？
 * 解答：因为这个时候 数据还是被锁着在 notify是不能释放锁的  在生产者里面第一行 一直不满足wait条件
 */
public class ProductsAndConsumers2<T> {
    final private LinkedList<T> list = new LinkedList<>();

    final private int MAX = 10;

    private int count=0;


    private Lock lock=new ReentrantLock();

    private Condition products=lock.newCondition();

    private Condition consumers=lock.newCondition();

    public void put(T t){
        lock.lock();
        while(list.size()==MAX){
            try {
                products.await();
                System.out.println("我是生产者我阻塞了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        count++;
        // 放满10个
        System.out.println(Thread.currentThread().getName()+"放了"+count+"个");
        consumers.signalAll();// 通知消费者线
        lock.unlock();
    }

    public T gett(){
        lock.lock();
        T t=null;
        while (list.size()==0){
            try {
                consumers.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t=list.removeFirst();
        count--;
        System.out.println(Thread.currentThread().getName()+"消费"+count+"个");
        products.signalAll();
        lock.unlock();
        return  t;
    }

    public static void main(String[] args) throws InterruptedException {
        ProductsAndConsumers2 pac2=new ProductsAndConsumers2();

        for(int i=0;i<2;i++){
            new Thread(()->{
                for(int j=0;j<10;j++){
                    pac2.put(new Object());
                }
            },"product"+i).start();
        }

        Thread.sleep(50);
        for(int i=0;i<10;i++){
            new Thread(()->{
                for(int j=0;j<2;j++){
                    pac2.gett();
                }
            },"consumers"+i).start();
        }

    }

}
