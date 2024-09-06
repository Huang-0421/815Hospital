package com.nine.backend.service;

import com.nine.backend.pojo.Patient;
import com.nine.backend.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;


/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/20 上午11:18
 */
public interface LoginService {
    //显示图像验证码
    void captcha(HttpServletRequest request,HttpServletResponse response) throws IOException;
    //登录
    Result login(HttpServletRequest request, HttpServletResponse response) throws Exception;
    //获取短信验证码
    Result sendSms(HttpServletRequest request) throws Exception;
    //注册
    Result signIn(HttpServletRequest request);
    //登出
    Result logout(HttpServletResponse response);
}