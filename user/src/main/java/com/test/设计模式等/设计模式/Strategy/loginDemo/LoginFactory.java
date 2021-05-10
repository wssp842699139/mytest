package com.test.设计模式等.设计模式.Strategy.loginDemo;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/5/4 16:09
 * @package com.test.设计模式等.设计模式.Strategy.loginDemo
 */
public class LoginFactory {
    public LoginStrategy CreateLogin(int type) {
       if (type==1){
           return new Type1Login();
       }else if (type==2){
           return new Type2Login();
       }else if (type==3){
           return new Type3Login();
       }
       return null;
    }
}
