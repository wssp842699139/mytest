package com.test.设计模式等.设计模式.Strategy.demo1;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/4/22 20:54
 * @package com.test.excel.设计模式.Strategy.线程集合Test
 */
public class DiscountCalculationFactory {

    /**
     * 根据优惠参数返回不同优惠计算策略逻辑
     *
     * @param calculationType 优惠类型
     * @return 优惠计算策略逻辑
     */
    public DiscountCalculation discountCalculation(Integer calculationType) {
        System.out.println("优惠计算策略参数为：" + calculationType);
        if (calculationType == 1) {
            return new FullReductionDiscountCalculation();
        } else if (calculationType == 2) {
            return new GroupBuyDiscountCalculation();
        } else if (calculationType == 3) {
            return new SpikeDiscountCalculation();
        } else {
            return new DefaultDiscountCalculation();
        }
    }
}
