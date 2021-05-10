package com.test.设计模式等.设计模式.observe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Observable;
import java.util.Observer;

@Component
public class Observe3 implements Observer {
    @Autowired
    Observeable observeable;

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("观察者3触发");
    }
    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        observeable.addObserver(this);
    }
}
