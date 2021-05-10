package com.test.设计模式等.设计模式.observe;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Observable;
import java.util.Observer;

@Component
public class Observe2 implements Observer ,InitializingBean {
    @Autowired
    Observeable observeable;

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("观察者2触发");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        observeable.addObserver(this);
    }
}
