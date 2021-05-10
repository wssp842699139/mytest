package com.test.设计模式等.设计模式.Strategy.demo2;

/**
 * VIP0Strategy:普通会员策略
 *
 * @author zhangxiaoxiang
 * @date: 2019/05/24
 */
public class VIP0Strategy implements Strategy {
    /**
     * 输入一个价格,经过VIP0Strategy策略计算价格
     * @param standardPrice
     * @return
     */
    @Override
    public double getPrice(double standardPrice) {
        System.out.println("[策略模式]普通会员 原价:"+standardPrice);
        return standardPrice;
    }

}
