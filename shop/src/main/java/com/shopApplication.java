package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/7/13 19:46
 * @package PACKAGE_NAME
 */
@SpringBootApplication
public class shopApplication {
    public static void main(String[] args) {
        SpringApplication.run(shopApplication.class,args);
    }

    @Bean
    public JedisPool jedisPool() {
        //JedisPoolConfig jedisPoolConfig = setPoolConfig(redisPoolMaxIdle, redisPoolMinIdle, redisPoolMaxActive, redisPoolMaxWait, true);
        return new JedisPool();
    }
}
