package com.test.设计模式等.设计模式.Strategy.demo3;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/4/5 18:21
 * @package com.test.excel.设计模式.Strategy
 */
public class GivenGreenLight implements  Strategy {
    @Override
    public void operate() {
        System.out.println("求吴国太开个绿灯，放行");
    }
}
