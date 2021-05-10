package com.test.dubboTest;

import com.test.service.RabbitDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/4/29 8:01
 * @package com.test.dubboTest
 */

@RestController("/test/dubbo")
public class RabbitDubboController {
    @Autowired
    private RabbitDubboService rabbitDubboService;

    @PostMapping
    public ResponseEntity testUpdate(

    ){
        rabbitDubboService.testUpdate();
        return ResponseEntity.status(200).body("");
    }

}
