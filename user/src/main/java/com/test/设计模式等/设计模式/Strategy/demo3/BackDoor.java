package com.test.设计模式等.设计模式.Strategy.demo3;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/4/5 18:20
 * @package com.test.excel.设计模式.Strategy
 */
public class BackDoor implements Strategy {
    @Override
    public void operate() {
        System.out.println("找乔国老帮忙，让吴国太给孙权施加压力，使孙权不能杀刘备");
    }
}
