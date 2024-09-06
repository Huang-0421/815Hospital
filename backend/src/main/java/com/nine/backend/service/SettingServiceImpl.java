package com.nine.backend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.nine.backend.mapper.DoctorMapper;
import com.nine.backend.mapper.PatientMapper;
import com.nine.backend.pojo.Doctor;
import com.nine.backend.pojo.Patient;
import com.nine.backend.utils.AliOSSUtils;
import com.nine.backend.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/22 下午7:22
 */
@Service
public class SettingServiceImpl implements SettingService {
    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private AliOSSUtils aliOSSUtils;
    @Autowired
    private DoctorMapper doctorMapper;


    @Override
    public Result setPatientInfo(HttpServletRequest request){
        Long patientId = Long.parseLong(request.getSession().getAttribute("patientId").toString());
        String name = request.getParameter("name");
        String idCard = request.getParameter("idCard");
        Integer gender = Integer.parseInt(request.getParameter("gender"));
        QueryWrapper<Patient> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", patientId);
        Patient patient = patientMapper.selectOne(queryWrapper);
        patient.setUsername(name);
        patient.setIdCard(idCard);
        patient.setGender(gender);
        patientMapper.update(patient, queryWrapper);
        return Result.success();
    }

    @Override
    public Result showReady(HttpServletRequest request) {
        Long patientId = Long.parseLong(request.getSession().getAttribute("patientId").toString());
        QueryWrapper<Patient> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", patientId);
        Patient patient = patientMapper.selectOne(queryWrapper);
        Map<String, String> map = new HashMap<>();
        map.put("name",patient.getUsername());
        map.put("idcard",patient.getIdCard());
        map.put("gender",patient.getGender().toString());
        map.put("phone",patient.getPhone());
        map.put("address",patient.getAddress());
        return Result.success(map);
    }

    @Override
    public Result setDocinfo(HttpServletRequest request) throws IOException {
        Long doctorId = Long.parseLong(request.getSession().getAttribute("doctorId").toString());
        String title = request.getParameter("title");
        String biography = request.getParameter("biography");
        Long departmentId = Long.parseLong(request.getParameter("departmentId"));
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile image = multipartRequest.getFile("image");
        String url = null;
        if (image != null) {
            url = aliOSSUtils.upload(image);
        }
        Doctor doctor = new Doctor();
        doctor.setTitle(title);
        doctor.setBiography(biography);
        doctor.setDepartmentId(departmentId);
        doctor.setImage(url);
        UpdateWrapper<Doctor> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", doctorId);
        doctorMapper.update(doctor, updateWrapper);
        return Result.success();
    }
}
