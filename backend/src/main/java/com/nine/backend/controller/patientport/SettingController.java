package com.nine.backend.controller.patientport;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nine.backend.mapper.PatientMapper;
import com.nine.backend.pojo.Patient;
import com.nine.backend.service.SettingService;
import com.nine.backend.utils.AliOSSUtils;
import com.nine.backend.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;

/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/22 下午7:20
 */
@RestController
public class SettingController {
    @Autowired
    SettingService settingService;
    @Autowired
    private AliOSSUtils aliOSSUtils;
    @Autowired
    private PatientMapper patientMapper;

    @PostMapping("/patient/setinfo")
    public Result setInfo(HttpServletRequest request) {
        return settingService.setPatientInfo(request);
    }

    @PostMapping("/patient/setimage")
    public Result setImage(HttpServletRequest request) throws IOException {
        Long patientId = Long.parseLong(request.getSession().getAttribute("patientId").toString());
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile image = multipartRequest.getFile("image");
        QueryWrapper<Patient> patientQueryWrapper = new QueryWrapper<>();
        patientQueryWrapper.eq("id", patientId);
        Patient patient = patientMapper.selectOne(patientQueryWrapper);
        String url = null;
        if (image != null) {
            url = aliOSSUtils.upload(image);
        }
        patient.setId(patientId);
        patient.setPhoto(url);
        patientMapper.updateById(patient);
        return Result.success();
    }

    @GetMapping("/patient/showready")
    public Result showReady(HttpServletRequest request) {
        return settingService.showReady(request);
    }

    @PostMapping("/doctor/setinfo")
    public Result setinfo(HttpServletRequest request) throws IOException{
        return settingService.setDocinfo(request);
    }
}
