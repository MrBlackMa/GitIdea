/**
 * ��ʶCallable����Runnable��������չ
 * ��Callable�ĵ��ã������з���ֵ
 */
package com.malixi.ThreadPooldemo;

import java.util.concurrent.Callable;
import java.util.concurrent.*;

public class T03_Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        java.util.concurrent.Callable<String> c = new Callable() {
            @Override
            public String call() throws Exception {
                return "Hello Callable";
            }
        };

        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit(c); //�첽

        System.out.println(future.get());//����

        service.shutdown();
    }

}
