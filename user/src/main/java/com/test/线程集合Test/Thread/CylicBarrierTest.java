package com.test.线程集合Test.Thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CylicBarrierTest {
    //定义屏障线程数为5
    private static CyclicBarrier barrier = new CyclicBarrier(2);

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 6; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executor.execute(() -> {
                try {
                    race(threadNum);
                } catch (Exception e) {
                    System.out.println();
                }
            });
        }
        executor.shutdown();
    }
    private static void race(int threadNum) throws Exception {
        Thread.sleep(1000);
        System.out.println("准备");
        //完成线程等待设定的屏障数为5时，往下执行，不足为5时 再次等待
        barrier.await();
        System.out.println("完成了");
    }
}
