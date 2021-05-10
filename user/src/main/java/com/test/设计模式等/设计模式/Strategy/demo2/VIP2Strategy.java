package com.test.设计模式等.设计模式.Strategy.demo2;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/5/4 13:39
 * @package com.test.设计模式等.设计模式.Strategy.demo2
 */
public class VIP2Strategy implements Strategy {
    /**
     * 输入一个价格,经过VIP2Strategy策略计算价格
     * @param standardPrice
     * @return
     */
    @Override
    public double getPrice(double standardPrice) {
        System.out.println("[策略模式]二级会员八折:"+standardPrice*0.8);
        return standardPrice*0.8;
    }

}
