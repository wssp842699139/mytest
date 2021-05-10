package com.test.设计模式等.设计模式.Strategy.loginDemo;

import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/5/4 15:34
 * @package com.test.设计模式等.设计模式.Strategy.loginDemo
 */
public class Type3Login implements LoginStrategy {
    @Override
    public Map<String, Object> doLogin(Integer type,Map<String, Object> parm) {
        Map<String, Object> a = new HashMap<>();
        System.out.println("第三种类型登录");
        Object username = parm.get("username");
        System.out.println("登陆的账号是"+username);
        return a;
    }
}
