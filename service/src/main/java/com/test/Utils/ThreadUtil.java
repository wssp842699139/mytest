package com.test.Utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Objects;
import java.util.concurrent.*;

public enum ThreadUtil {
    INSTANCE;

    private ExecutorService executor;

    ThreadUtil() {
        if (Objects.isNull(executor)) {
            int MAX_POOL_SIZE = 500;
            int capacity = 1024;
            String threadName = "report-service-Thread-%d";
            ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat(threadName).build();
            executor = new ThreadPoolExecutor(0, MAX_POOL_SIZE,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>(capacity), threadFactory, new ThreadPoolExecutor.AbortPolicy());
        }
    }
    public ExecutorService getInstance(){
        return executor;
    }
}

