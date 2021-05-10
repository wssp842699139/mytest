package com.test.设计模式等.设计模式.singlton;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/4/5 13:31
 * @package com.test.excel.设计模式.singlton
 */
public class Singleton5 {
    // 私有内部类，按需加载，用时加载，也就是延迟加载
    private static class Holder {
        //静态内部内不会自动初始化 只有调用静态内部类的方法，静态域，或者构造方法的时候才会加载静态内部类 , 所以能实现懒加载
        private static Singleton5 singleton5 = new Singleton5();
    }

    private Singleton5() {

    }

    public static Singleton5 getSingleton5() {
        return Holder.singleton5;
    }
}
