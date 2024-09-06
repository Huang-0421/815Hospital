package com.nine.backend.controller.adminport;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nine.backend.pojo.Doctor;
import com.nine.backend.pojo.Patient;
import com.nine.backend.service.AdminService;
import com.nine.backend.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/25 上午11:57
 */
@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    //admin向医生发送通知
    @PostMapping("/admin/sendmsg")
    public Result sendMsgToDoc(HttpServletRequest request){
        return adminService.sendMsgToDoc(request);
    }
    //admin登录
    @PostMapping("/admin/login")
    public Result adminLogin(HttpServletRequest request,HttpServletResponse response) {
        return adminService.AdminLogin(request,response);
    }
    //admin设置医生在岗状态
    @PostMapping("/admin/duty")
    public Result duty(HttpServletRequest request) {
        return adminService.duty(request);
    }
    //编辑医生信息
    @PostMapping("/admin/editdoc")
    public Result editDoc(HttpServletRequest request) {
        return adminService.editDoc(request);
    }
    //删除医生
    @PostMapping("/admin/deletedoc")
    public Result deleteDoc(HttpServletRequest request) {
        return adminService.deleteDoc(request);
    }
    //模糊查询医生
    @PostMapping("/admin/searchdoc")
    public Result searchDoc(HttpServletRequest request) {
        return adminService.searchDoc(request);
    }
    //模糊查询药品
    @PostMapping("/admin/searchmedi")
    public Result searchMedi(HttpServletRequest request) {
        return adminService.searchMedi(request);
    }
    //搜索流水
    @PostMapping("/admin/searchrun")
    public Result searchRun(HttpServletRequest request) {
        return adminService.searchRun(request);
    }
}
