package com.malixi.mianshi;

import java.util.LinkedList;
import java.util.List;

public class MianShi1 {

   //  加volatile 也可以满足需求完成当前任务
  //volatile
   List list=new LinkedList<>();

   int count=0;

    public void  add(Object object){
         list.add(object);
    }

    public int size(){
        return list.size();
    }
    public static void main(String[] args) {
        MianShi1 list1=new MianShi1();
           Thread t1=new Thread(()->{
               for(int i=0;i<10;i++){
                   list1.count++;
                   list1.add(new Object());
                   System.out.println(list1.size());
                   /**  如果不加上这个sleep这句话,list增加到10了 可能t2才执行到while(true);
                        因为t1 在前面启动 所以没有volatile这句增加线程的可见性 永远监听不到list.size==5
                     */
                   try {
                       Thread.sleep(1000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               System.out.println("线程t1结束");
           },"t1");

           Thread t2=new Thread(()->{
               // t1 启动的时候 t2也启动了 然后t2的run方法其实已经跑了
               // 根据线程默认的不可见性  永远监听不到size==5的情况
               // 但是加上了sys.out的话 或者加上volatile 增加线程的可见行
               System.out.println(list1.count);
               while(true){
                    //加这一句话 照样可以停止 满足需求
                    //System.out.println(list1.size());
                   if(list1.size()==5){
                       System.out.println(list1.size());
                       break;
                   }

               }
               System.out.println("线程2结束");
           },"t2");

           t1.start();
           t2.start();
    }


}
