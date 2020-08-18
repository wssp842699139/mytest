package com.test.controller;

import com.test.Utils.MD5Utils;
import com.test.exception.RemotionServiceException;
import com.test.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/8/17 19:44
 * @package com.test.controller
 */
@RestController
@RequestMapping("/user/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public ResponseEntity login(
            @RequestParam(required = true)String username,
            @RequestParam(required = true)String password
    ){
        try {
            loginService.login(username,password);
            return ResponseEntity.status(200).body("登录成功");
        } catch (RemotionServiceException e) {
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        }
    }


    @RequestMapping("/register")
    public ResponseEntity register(
            @RequestParam(required = true ) String username,
            @RequestParam(required = true) String password
    ) throws RemotionServiceException {
        String savePassword = MD5Utils.md5Password(password);
        try {
            loginService.register(username,savePassword);
            return ResponseEntity.status(200).body("注册成功");
        } catch (RemotionServiceException e) {
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        }

    }
}
