package com.test.Utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *线程池相关
 */
@Configuration
public class ThreadPoolConfig {
    @Value("${thread.pool.core.pool.size:10}")
    private int threadPoolCorePoolSize;
//    @Value("thread.pool.max.pool.size:50")
//    private int threadPoolMaxPoolSize;
//    @Value("thread.pool.queue.capacity:50")
//    private int threadPoolQueueCapacity;
//    @Value("thread.pool.keep.alive.seconds:300")
//    private int threadPoolKeepAliveSeconds;

    @Bean("ScheduledThreadPool")
    public ExecutorService ScheduledThreadPool(){
        return  Executors.newScheduledThreadPool(threadPoolCorePoolSize);
    }
    @Bean("FixedThreadPool")
    public ExecutorService FixedThreadPool(){
        return  Executors.newFixedThreadPool(threadPoolCorePoolSize);
    }
    @Bean("CachedThreadPool")
    public ExecutorService CachedThreadPool(){
        return  Executors.newCachedThreadPool();
    }
    @Bean("SingleThreadExecutor")
    public ExecutorService SingleThreadExecutor(){
        return  Executors.newSingleThreadExecutor();
    }

}