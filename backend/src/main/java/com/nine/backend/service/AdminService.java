package com.nine.backend.service;
import com.nine.backend.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/25 上午11:58
 */
public interface AdminService {
    Result sendMsgToDoc(HttpServletRequest request);
    Result AdminLogin(HttpServletRequest request, HttpServletResponse response);
    Result duty(HttpServletRequest request);
    Result editDoc(HttpServletRequest request);
    Result deleteDoc(HttpServletRequest request);
    Result searchDoc(HttpServletRequest request);
    Result searchMedi(HttpServletRequest request);
    Result searchRun(HttpServletRequest request);
}
