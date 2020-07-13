package com.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/7/13 19:46
 * @package com
 */
@RestController
public class test {
    @RequestMapping("/test")
    public String test1(){
        return "success";
    }
}
