package com.malixi;

import java.util.concurrent.atomic.AtomicInteger;

public class IntegerEum {
    private static final IntegerEum integerEum = new IntegerEum();
    public static  AtomicInteger at=new AtomicInteger(100000);

    public int ticket = 5;

    public static Object ob=new Object();


    public IntegerEum() {
    }

    public static IntegerEum getIntegerEum() {
        return integerEum;
    }

    public static void main(String[] args) {
        System.out.println(at.getAcquire());
        System.out.println(at.intValue());
        System.out.println(at.addAndGet(0));
        System.out.println(IntegerEum.getIntegerEum().ticket);
    }
}
