package com.test.设计模式等.设计模式.Strategy.loginDemo;

import java.util.Map;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/5/4 15:36
 * @package com.test.设计模式等.设计模式.Strategy.loginDemo
 */
public class LoginContext {
    /**
     * 当前采用的算法对象
     * 面向接口,组合编程,少用继承
     * 简言之复杂类型(类,接口等)做属性
     */
    private LoginStrategy strategy;

    /**
     * 选择策略Strategy实现类
     * 有参构造器(不写无参构造器,那么new 策略实现保证必须传一种策略,这里set方法也不用设置,
     * 设置了也没用(要设置set方法那么还是把无参构造也写出来才会有用,所以set伴随无参构造的感觉)
     * 这样同时也知道了为什么有参构造器设置了为什么无参构造器就失效了,JDK这样设计是有一定道理的,哈哈)
     * ---总之set注入也行,而且也推荐,也是一种组合/聚合的形式,只是这个例子采用构造器而已
     * @param strategy
     */
    public LoginContext(LoginStrategy strategy) {
        this.strategy = strategy;
    }

    public Map<String, Object> doLogin(Integer type,Map<String, Object> parm){
        return this.strategy.doLogin(type,parm);
    }
    //我的例子没有使用set方式注入而已,也可以使用它哈
    // public void setStrategy(Strategy strategy) {
    // 	this.strategy = strategy;
    // }
}
