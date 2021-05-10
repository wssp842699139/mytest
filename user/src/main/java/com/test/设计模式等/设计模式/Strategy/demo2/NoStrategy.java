package com.test.设计模式等.设计模式.Strategy.demo2;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/5/4 13:37
 * @package com.test.设计模式等.设计模式.Strategy.demo2
 */
public class NoStrategy {
    /**
     * 传入客服等级类型获取相应的价格
     * @param type   会员类型(等级)
     * @param price  响应的价格
     * @return
     */
    public double getPrice(String type, double price) {

        if ("普通客户小批量".equals(type)) {
            System.out.println("[未采用设计模式] 不打折,原价");
            return price;
        } else if ("普通客户大批量".equals(type)) {
            System.out.println("[未采用设计模式] 打九折");
            return price * 0.9;
        } else if ("老客户小批量".equals(type)) {
            System.out.println("[未采用设计模式] 打八折");
            return price * 0.8;
        } else if ("老客户大批量".equals(type)) {
            System.out.println("[未采用设计模式] 打七折");
            return price * 0.7;


            //拓展一种策略
        }else if("老客户特大批量".equals(type)){
            System.out.println("[未采用设计模式] 打六折");
            return price*0.6;
        }


        //乱传的也是当普通客户小批量(就是不打折)
        return price;
    }

}

