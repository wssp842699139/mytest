package com.test.设计模式等.设计模式.singlton;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/4/5 13:10
 * @package com.test.excel.设计模式.singlton
 */
public class MySinglton {

    /*私有的构造方法；

    指向自己实例的私有静态引用；

    以自己实例为返回值的静态的公有方法。*/
    private static MySinglton mySinglton;

    private MySinglton() {
    }

    public static MySinglton getInstence() {
        if (null == mySinglton) {
            synchronized (MySinglton.class) {
                if (null == mySinglton) {
                    mySinglton = new MySinglton();
                }
            }
        }
        return mySinglton;
    }
}
