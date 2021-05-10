package com.test.dubboTest;

import com.test.dao.RabbitDubboDao;
import com.test.service.RabbitDubboService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/4/29 8:24
 * @package com.test.dubboTest
 */

@Service
public class RabbitDubboServiceImpl implements RabbitDubboService {
    @Autowired
    private RabbitDubboDao testDubboDao;

    public void testUpdate() {
        testDubboDao.testUpdate();
        throw new RuntimeException();
    }
}
