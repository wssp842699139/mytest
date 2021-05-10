package com.test.设计模式等.设计模式.Strategy.demo1;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/4/22 20:55
 * @package com.test.excel.设计模式.Strategy.线程集合Test
 */
/**
 * @program: excel
 * @description: 策略逻辑调用计算封装类
 * @author: Kenny.Qiu
 * @create: 2020-08-08 15:46
 */
public class DiscountCalculationContext {

    /**
     * 优惠策略逻辑接口
     */
    private DiscountCalculation discountCalculation;

    public DiscountCalculation getDiscountCalculation() {
        return discountCalculation;
    }

    public void setDiscountCalculation(DiscountCalculation discountCalculation) {
        this.discountCalculation = discountCalculation;
    }

    /**
     * 调用不同优惠策略逻辑方法
     */
    public void calculation() {
        discountCalculation.calculationDiscount();
    }
}

