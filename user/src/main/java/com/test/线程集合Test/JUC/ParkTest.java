package com.test.线程集合Test.JUC;

import java.util.concurrent.locks.LockSupport;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/5/3 1:39
 * @package com.test.线程集合Test.JUC
 */
public class ParkTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("start...");
            try {
                Thread.sleep(2000);//t1睡眠了一秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("park...");
            LockSupport.park();//t1线程一秒后暂停
            LockSupport.park();//t1线程一秒后暂停
            System.out.println("reuser...");
        }, "t1");
        t1.start();

        Thread.sleep(1000);//主线程睡眠二秒
        System.out.println("unpark...");
        LockSupport.unpark(t1);//二秒后由主线程恢复t1线程的运行

    }
}
