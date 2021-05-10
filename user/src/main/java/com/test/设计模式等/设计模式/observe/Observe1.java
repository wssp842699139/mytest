package com.test.设计模式等.设计模式.observe;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Observable;
import java.util.Observer;

@Component
public class Observe1 implements Observer ,InitializingBean {

    @Autowired
    Observeable observeable;


    @Override
    public void update(Observable o, Object arg) {
        System.out.println("观察者1触发"+arg.toString());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
        observeable.addObserver(this);
    }

    @PostConstruct
    public void test() throws Exception {
        System.out.println("PostConstruct");
        observeable.addObserver(this);
    }
}
