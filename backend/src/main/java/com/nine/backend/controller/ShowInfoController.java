package com.nine.backend.controller;
import com.nine.backend.service.ShowInfoService;
import com.nine.backend.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ShowInfoController {

    @Autowired
    private ShowInfoService showInfoService;

    //展示患者个人信息
    @GetMapping("/patient/showpatient")
    public Result getPatientById(HttpServletRequest httpServletRequest) {
        System.out.println("已经收到get");
        return showInfoService.getPatientById(httpServletRequest);
    }
    //展示所有挂号医生
    @GetMapping("/patient/showdoc")
    public Result getDoc(HttpServletRequest httpServletRequest) {
        return showInfoService.getDoc(httpServletRequest);
    }
    //展示患者个人缴费单
    @GetMapping("/patient/showpayment")
    public Result getPayment(HttpServletRequest httpServletRequest) {
        return showInfoService.getPayment(httpServletRequest);
    }
    //完成缴费
    @PostMapping("/patient/dopayment")
    public Result getPayment(@RequestBody Map<String, List<String>> requestBody) {
        return showInfoService.doPayment(requestBody);
    }
    //展示患者个人挂号单
    @GetMapping("/patient/showreg")
    public Result getRegistration(HttpServletRequest httpServletRequest) {
        return showInfoService.getRegistration(httpServletRequest);
    }
    //展示意见反馈
    @GetMapping("/patient/showfeedback")
    public Result showFeedback(HttpServletRequest request){
        return showInfoService.showFeedback(request);
    }
    //展示医生所有病人
    @GetMapping("/doctor/showpatients")
    public Result getPatients(HttpServletRequest httpServletRequest) {
        return showInfoService.getPatients(httpServletRequest);
    }
    //展示医生个人信息
    @GetMapping("/doctor/showme")
    public Result showMe(HttpServletRequest request) {
        return showInfoService.showMe(request);
    }
    //展示所有来自管理员的通知
    @GetMapping("/doctor/shownoti")
    public Result getNoti(HttpServletRequest httpServletRequest) {
        return showInfoService.getNoti(httpServletRequest);
    }

    //展示所有诊断
    @GetMapping("/doctor/showpay")
    public Result getPay(HttpServletRequest httpServletRequest) {
        return showInfoService.getPay(httpServletRequest);
    }
    //搜索并展示药品及余量
    @GetMapping("/doctor/showmedi")
    public Result getMedical(HttpServletRequest httpServletRequest) {
        return showInfoService.getMedical(httpServletRequest);
    }
    //患者分页展示
    @PostMapping("/admin/showpatient")
    public Result pageShowPatient(HttpServletRequest request){
        return showInfoService.pageShowPatient(request);
    }
    //医生分页查询
    @PostMapping("/admin/showdoctor")
    public Result pageShowDoctor(HttpServletRequest request){
        return showInfoService.pageShowDoctor(request);
    }
    //显示所有医生
    @GetMapping("/admin/showalldoctor")
    public Result showAllDoc(){
        return showInfoService.showAllDoc();
    }

    //分页查询反馈列表
    @PostMapping("/admin/showfeedback")
    public Result pageShowFeedback(HttpServletRequest request){
        return showInfoService.pageShowFeedback(request);
    }

    //搜索并展示药品及余量
    @PostMapping("/admin/showmedi")
    public Result showMedical(HttpServletRequest httpServletRequest) {
        return showInfoService.showMedical(httpServletRequest);
    }
    //分页查询流水
    @PostMapping("/admin/showrun")
    public Result pageShowRun(HttpServletRequest request) {
        return showInfoService.pageShowRun(request);
    }

    //大屏展示
    @GetMapping("/admin/showall")
    public Result showAll(HttpServletRequest request){
        return showInfoService.showAll(request);
    }
}
