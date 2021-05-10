package com.test.设计模式等.设计模式.prox;

public class prox3 implements  prox1 {
    private prox1 prox1;

    public prox3(){
        this.prox1 = new prox2();
    }

    @Override
    public void est() {
        System.out.println("制造勺子");
        prox1.est();
        System.out.println("吃完了");
    }
}
