package com.test.设计模式等.设计模式.Strategy.demo1;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/4/22 20:53
 * @package com.test.excel.设计模式.Strategy.线程集合Test
 */
public class GroupBuyDiscountCalculation implements DiscountCalculation {
    /**
     * 计算商品优惠价格
     */
    @Override
    public void calculationDiscount() {
        System.out.println("团购优惠计算策略模式");
    }
}
