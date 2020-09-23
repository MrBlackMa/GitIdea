package com.malixi.Singleton;

public class LazySington {
    private static LazySington lazySington;

    private LazySington(){

    }

    public static LazySington getLazySington(){
              if(lazySington==null){
                  lazySington=new LazySington();
              }
              return  lazySington;
    }
}
