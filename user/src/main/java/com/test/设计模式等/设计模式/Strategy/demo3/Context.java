package com.test.设计模式等.设计模式.Strategy.demo3;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/4/5 18:22
 * @package com.test.excel.设计模式.Strategy
 */
public class Context {
    private Strategy strategy;
    //构造函数，要你使用哪个妙计
    public Context(Strategy strategy){
        this.strategy = strategy;
    }
    public void setStrategy(Strategy strategy){
        this.strategy = strategy;
    }
    public void operate(){
        this.strategy.operate();
    }

}
