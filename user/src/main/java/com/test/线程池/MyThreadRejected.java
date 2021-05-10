package com.test.线程池;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/5/3 5:42
 * @package com.test.线程池
 */
public class MyThreadRejected implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println(r.toString()+"被丢弃");
    }
}
