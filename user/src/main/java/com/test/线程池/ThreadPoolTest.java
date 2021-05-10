package com.test.线程池;

import java.util.concurrent.*;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/5/3 4:57
 * @package com.test.线程集合Test.Thread
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
       //ExecutorService service = Executors.newFixedThreadPool(10);
        //ExecutorService service1 = Executors.newSingleThreadExecutor();
        //ExecutorService service2 = Executors.newCachedThreadPool();
        //ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 50; i++) {
            threadPoolExecutor.execute(
                    () -> {
                        System.out.println(Thread.currentThread().getName() + "运行");
                        // Thread.sleep(2000L);
                    }
            );
        }
        System.out.println("主线程启动");

    }
}
