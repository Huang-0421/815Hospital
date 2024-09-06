package com.nine.backend.controller;

import com.nine.backend.mapper.SignInMapper;
import com.nine.backend.pojo.Patient;
import com.nine.backend.service.LoginService;
import com.nine.backend.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/19 上午11:07
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    //显示图像验证码
    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request,HttpServletResponse response) throws IOException {
        loginService.captcha(request,response);
    }

    //登录
    @PostMapping("/login")
    public Result login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return loginService.login(request,response);
    }

    //获取短信验证码
    @PostMapping("/sendSms")
    public Result sendSms(HttpServletRequest request) throws Exception {
        return loginService.sendSms(request);
    }

    //注册
    @PostMapping("/sign")
    public Result signIn(HttpServletRequest request) {
        return loginService.signIn(request);
    }

    @GetMapping("/logout")
    public Result logout(HttpServletResponse response) {
        return loginService.logout(response);
    }
}