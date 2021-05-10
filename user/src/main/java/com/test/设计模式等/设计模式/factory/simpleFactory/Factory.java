package com.test.设计模式等.设计模式.factory.simpleFactory;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/5/4 15:49
 * @package com.test.设计模式等.设计模式.factory.simpleFactory
 */
public class Factory {
    public BMW CreateBMW(int type) {
        if (type == 100) return new BMW100();
        if (type == 200) return new BMW200();
        else return null;
    }
}

class test {
    public static void main(String[] args) {
        Factory factory = new Factory();
        BMW bmw = factory.CreateBMW(100);
        bmw.BMW();
    }
}
