package com.test.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.test.dao.TestDubboDao;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/4/29 7:46
 * @package com.test.service
 */
@Service
@Log4j
public class TestDubboService {

    @Autowired
    TestDubboDao testDubboDao;

    @Reference
    private RabbitDubboService rabbitDubboService;

    public void testUpdate() {
        testDubboDao.testUpdate();
        System.out.println("扣钱成功");
        rabbitDubboService.testUpdate();
        System.out.println("扣减数量成功");
    }
}
