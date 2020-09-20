package com.proxy;

public class Wangpo implements KindWomen {
    private KindWomen kindWomen;
    public Wangpo (){
        this.kindWomen=new Panjinlian();
    }

    public Wangpo(KindWomen kindWomen){
        this.kindWomen=kindWomen;
    }


    @Override
    public void paomeiyan() {
       this.kindWomen.paomeiyan();
    }

    @Override
    public void touqing() {
       this.kindWomen.touqing();
    }
}
