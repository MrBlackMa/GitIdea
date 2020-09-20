package com.proxy;

public class XImenqing {
    public static void main(String[] args) {
        Wangpo wangpo=new Wangpo();
        wangpo.paomeiyan();
        wangpo.touqing();

        Jiashi jiashi=new Jiashi();
        Wangpo wangpo1=new Wangpo(jiashi);
        wangpo1.touqing();
        wangpo1.paomeiyan();

    }
}
