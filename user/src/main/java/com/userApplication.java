package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/6/1 21:18
 * @package com.test
 */
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class userApplication {
    public static void main(String[] args) {
        SpringApplication.run(userApplication.class,args);
    }
}
