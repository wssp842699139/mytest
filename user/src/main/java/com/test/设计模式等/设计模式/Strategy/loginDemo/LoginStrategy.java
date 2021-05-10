package com.test.设计模式等.设计模式.Strategy.loginDemo;

import java.util.Map;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/5/4 15:31
 * @package com.test.设计模式等.设计模式.Strategy.loginDemo
 */
public interface LoginStrategy {

    Map<String,Object> doLogin (Integer type , Map<String,Object> parm);
}
