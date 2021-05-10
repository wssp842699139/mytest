package com.test.设计模式等.设计模式.observe;

import org.springframework.stereotype.Component;

import java.util.Observable;

@Component
public class Observeable extends Observable {
    public void testOb(){
        System.out.println("业务完成 ,准备触发观察者");
        super.setChanged();
        super.notifyObservers("aaa");
    }
}
