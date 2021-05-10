package com.test;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/6/1 21:18
 * @package com.test
 */
@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableDubbo
@MapperScan(basePackages = {"com.test.dao","com.test.dao1"})
@EnableDiscoveryClient
@ServletComponentScan(basePackages = {"com.test.filter","com.test.config"})
public class userApplication {
    public static void main(String[] args) {
        SpringApplication.run(userApplication.class,args);
    }
}
