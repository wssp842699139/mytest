package com.test;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/8/7 16:25
 * @package PACKAGE_NAME
 */
@SpringBootApplication
@EnableDubbo
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.test.dao"})
@ServletComponentScan(basePackages = {"com.test.filter","com.test.config"})
public class testRabbitmqApplication {
    public static void main(String[] args) {
        SpringApplication.run(testRabbitmqApplication.class,args);
    }
}
