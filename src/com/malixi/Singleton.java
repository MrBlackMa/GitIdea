package com.malixi;

/**
 *  饿汉式单例
 *  线程安全
 */
public class Singleton {
    private static final Singleton sl=new Singleton();
    public Singleton(){
    }
    public static Singleton getSl() {
        return sl;
    }

    public static void main(String [] args){
        Singleton s1=getSl();
        Singleton s2=getSl();
        System.out.println(s1==s2);
        System.out.println(s1.equals(s2));
    }
}
