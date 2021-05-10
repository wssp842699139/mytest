package com.test.设计模式等.设计模式.Strategy.demo2;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/5/4 13:42
 * @package com.test.设计模式等.设计模式.Strategy.demo2
 */
public class VPI4Strategy implements Strategy {
    /**
     * 通过策略获取价格
     *
     * @param standardPrice
     * @return
     */
    @Override
    public double getPrice(double standardPrice) {
        System.out.println("[策略模式]新增的策略 打六折："+standardPrice * 0.6);
        return standardPrice * 0.6;
    }
}
