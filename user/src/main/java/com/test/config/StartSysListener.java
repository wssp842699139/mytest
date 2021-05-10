package com.test.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 设置mybatis自定义拦截器
 * pageHelper拦截器会设置缓存导致自定义拦截失效
 * 解决办法是把拦截器设置在pageHelper之前
 * 在容器启动完成后再添加自定义的拦截器
 *
 */
@Component
public class StartSysListener implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        sqlSessionFactory.getConfiguration().addInterceptor(mybatisInterceptor);
    }

    @Autowired
    private SqlInterceptor  mybatisInterceptor;
    @Resource(name = "test1SqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;


}
