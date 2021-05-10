package com.test.设计模式等.设计模式.Strategy.demo1;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/4/22 20:55
 * @package com.test.excel.设计模式.Strategy.线程集合Test
 */
public class StrategyModelDemo {
    public static void main(String[] args) {
        /**
         * calculationType: 1-满减；2-团购；3-秒杀；其他-默认
         */
        Integer calculationType = 1;
        // 基于满减类型参数，获取策略计算实现类
        DiscountCalculationFactory factory = new DiscountCalculationFactory();
        DiscountCalculation strategy = factory.discountCalculation(calculationType);
        // 封装了策略具体计算调用方法，简便用户操作调用
        DiscountCalculationContext calculationContext = new DiscountCalculationContext();
        calculationContext.setDiscountCalculation(strategy);
        calculationContext.calculation();
    }
}
