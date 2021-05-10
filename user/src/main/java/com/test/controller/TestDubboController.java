package com.test.controller;

import com.test.service.TestDubboService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/4/29 7:42
 * @package com.test.controller
 */
@RestController
@RequestMapping("/test/dubbo")
@Log4j
public class TestDubboController {
    @Autowired
    private TestDubboService testDubboService;



    @GetMapping("/test")
    public ResponseEntity testUpdate(

    ){
        System.out.println("进来了");
        testDubboService.testUpdate();
        return ResponseEntity.status(200).body("");
    }

}
