package com.malixi;

public class NoStaticMethod {

    public void demo1(){
        System.out.println("我是非静态方法");
    }

    public void demo2(){
        demo1();
        demo3();
    }

    public static void demo3(){
        System.out.println("我是静态方法");
    }

}
