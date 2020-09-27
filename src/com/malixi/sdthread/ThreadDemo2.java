package com.malixi.sdthread;

/**
 * 两种线程交替输出方法的一种
 * wait 可以释放锁 notify不能释放锁
 */
public class ThreadDemo2 {
   static char daxie='A';

    public static void main(String[] args) {
      Object object=new Object();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object){
                   // int j=0;
                    for(int i=0;i<26;i++){
                            // a0 0步
                            System.out.println(daxie);
                            daxie++;
                            try {
                                // t1进行阻塞 1步
                                object.wait();

                                //唤醒T2 执行完 开始循环执行t1 5步
                                object.notify();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                    }
                }
            }
        });
        t1.start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object){
                    for(int i=1;i<27;i++){
                        // 线程t2启动 输出b0 2步
                        System.out.println(i);
                        try {
                            // 线程T2 启动了 T2唤醒T1 3步
                            object.notify();
                            // T2 进入阻塞 等待t1唤醒 唤醒后进入循环 4步
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        t2.start();
    }


}
