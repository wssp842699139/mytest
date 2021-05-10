package com.test.设计模式等.设计模式.Strategy.loginDemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/5/4 15:37
 * @package com.test.设计模式等.设计模式.Strategy.loginDemo
 */
@RestController
@RequestMapping("/user/doLoginTest")
public class LoginTestController {

    @GetMapping(value = "/login")
    public Map<String, Object> login(
            @RequestParam(required = true)Integer type

    ){
        Map<String, Object> a = new HashMap<>();
        a.put("username","aaa");
        a.put("password",123);
        LoginStrategy loginStrategy = new LoginFactory().CreateLogin(type);
        LoginContext loginContext = new LoginContext(loginStrategy);
        Map<String, Object> stringObjectMap = loginContext.doLogin(type,a);
        return stringObjectMap;
    }
}
