package com.malixi;

public class IntegerEum {
    private static final IntegerEum integerEum = new IntegerEum();
    public int ticket = 5;

    public static Object ob=new Object();


    public IntegerEum() {
    }

    public static IntegerEum getIntegerEum() {
        return integerEum;
    }

    public static void main(String[] args) {

        System.out.println(IntegerEum.getIntegerEum().ticket);
    }
}
