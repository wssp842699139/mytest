package com.test.线程集合Test.JUC;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/5/3 1:57
 * @package com.test.线程集合Test.JUC
 */
public class InterruptTest {
    public static void main(String[] args) {
        System.out.println("begin run");
        //重要：无论是先中断还是先阻塞都能达到停止线程的目的，只要两者配置使用就可以到达效果
        //Thread.currentThread().interrupt();
        Thread.currentThread().interrupt();
        System.out.println(Thread.currentThread().isInterrupted());
        try {
            //方式一，线程进入sleep
            //Thread.sleep( 1000);
            //方式二、join
            //Thread.currentThread().join();
            //方式三、wait
            Thread.currentThread().wait();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().isInterrupted());
            e.printStackTrace();
        }



        System.out.println("begin end");
    }
}
