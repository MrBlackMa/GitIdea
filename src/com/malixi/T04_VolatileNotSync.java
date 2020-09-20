package com.malixi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class T04_VolatileNotSync {
   // volatile
    int count =0;
    //AtomicInteger count = new AtomicInteger(0);

   //synchronized
   void m() {
        for (int i = 0; i < 3000; i++)
        //	count.incrementAndGet();
        count++;
    }

    public static void main(String[] args) {
        T04_VolatileNotSync t = new T04_VolatileNotSync();

        List<Thread> threads = new ArrayList<Thread>();

        for (int i = 0; i < 3; i++) {
            threads.add(new Thread(t::m, "thread-" + i));
        }

        threads.forEach((o) -> o.start());

        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        System.out.println(t.count);


    }

}