package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/7/13 19:46
 * @package PACKAGE_NAME
 */
@SpringBootApplication
public class shopApplication {
    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(shopApplication.class,args);
    }
}
