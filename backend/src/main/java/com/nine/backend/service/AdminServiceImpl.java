package com.nine.backend.service;

import ch.qos.logback.core.util.StringUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nine.backend.mapper.*;
import com.nine.backend.pojo.*;
import com.nine.backend.utils.JwtUtils;
import com.nine.backend.utils.Result;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/25 上午11:59
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private MedicationMapper medicationMapper;
    @Autowired
    private RunningMapper runningMapper;

    @Override
    public Result sendMsgToDoc(HttpServletRequest request) {
        String content = request.getParameter("content");
        String doctorName = request.getParameter("doctorName");
        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", doctorName);
        Doctor doctor = doctorMapper.selectOne(queryWrapper);
        Notification notification = new Notification();
        notification.setContent(content);
        notification.setDoctorId(doctor.getId());
        notificationMapper.insert(notification);
        return Result.success();
    }

    @Override
    public Result AdminLogin(HttpServletRequest request, HttpServletResponse response) {
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        password = SecureUtil.md5(password);
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username)
                .eq("password", password);
        Admin admin = adminMapper.selectOne(queryWrapper);
        if (admin != null) {
            //生成JWT令牌
            Map<String, Object> claims = new HashMap<>();
            String jwt = JwtUtils.generateJWT(claims);
            //返回JWT
            Cookie cookie = new Cookie("token", jwt);
            cookie.setMaxAge(60 * 60);//有效期
            response.addCookie(cookie);
            Map<String, Object> map = new HashMap<>();
            return Result.success(map);
        } else return Result.error("账号密码错误");
    }

    @Override
    public Result duty(HttpServletRequest request) {
        Long doctorId = Long.parseLong(request.getParameter("doctorId"));
        UpdateWrapper<Doctor> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", doctorId);
        Doctor doctor = doctorMapper.selectOne(updateWrapper);
        int duty = doctor.getDuty();
        duty = duty == 1 ? 0 : 1;
        doctor.setDuty(duty);
        doctorMapper.update(doctor, updateWrapper);
        return Result.success();
    }

    @Override
    public Result editDoc(HttpServletRequest request) {
        String doctorName = request.getParameter("doctorName");
        String phone = request.getParameter("phone");
        Integer age = Integer.parseInt(request.getParameter("age"));
        Integer gender = Integer.parseInt(request.getParameter("gender"));
        Doctor doctor = new Doctor();
        doctor.setName(doctorName);
        doctor.setPhone(phone);
        doctor.setAge(age);
        doctor.setGender(gender);
        String departmentName = request.getParameter("departmentName");
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", departmentName);
        Long departmentId = departmentMapper.selectOne(queryWrapper).getId();
        doctor.setDepartmentId(departmentId);
        String doctorIdStr = request.getParameter("doctorId");
        if (StringUtil.notNullNorEmpty(doctorIdStr)) {
            Long doctorId = Long.parseLong(doctorIdStr);
            UpdateWrapper<Doctor> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", doctorId);
            doctorMapper.update(doctor, updateWrapper);
        } else {
            doctorMapper.insert(doctor);
        }
        return Result.success();
    }

    @Override
    public Result deleteDoc(HttpServletRequest request) {
        String doctorId = request.getParameter("doctorId");
        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", doctorId);
        doctorMapper.delete(queryWrapper);
        return Result.success();
    }

    @Override
    public Result searchDoc(HttpServletRequest request) {
        String doctorName = request.getParameter("doctorName");
        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
        if (doctorName != null && !doctorName.trim().isEmpty()) {
            queryWrapper.like("name", doctorName);
            Page<Doctor> page = new Page<>(1, 10);
            page = doctorMapper.selectPage(page, queryWrapper);
            List<Doctor> doctors = page.getRecords();
            List<Map<String, Object>> result = new ArrayList<>();
            Map<String, Object> result_map = new HashMap<>();
            long total = page.getTotal();
            for (Doctor doctor : doctors) {
                Map<String, Object> map = new HashMap<>();
                Long doctorId = doctor.getId();
                String doctorIdStr = doctorId.toString();
                map.put("id", doctorIdStr);
                map.put("name", doctor.getName());
                map.put("phone", doctor.getPhone());
                map.put("age", doctor.getAge());
                map.put("gender", doctor.getGender());
                map.put("departmentId", doctor.getDepartmentId());
                map.put("duty", doctor.getDuty());
                result.add(map);
            }
            result_map.put("doctors", result);
            result_map.put("total", total);
            return Result.success(result_map);
        } else return Result.success();
    }

    @Override
    public Result searchMedi(HttpServletRequest request) {
        String medicationName = request.getParameter("medicationName");
        if (StringUtil.isNullOrEmpty(medicationName)) {
            return Result.success();
        }
        QueryWrapper<Medication> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", medicationName);
        Page<Medication> page = new Page<>(1, 10);
        page = medicationMapper.selectPage(page, queryWrapper);
        List<Medication> records = page.getRecords();
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> result_map = new HashMap<>();
        long total = page.getTotal();
        for (Medication medication : records) {
            Map<String, Object> map = new HashMap<>();
            Long medicationId = medication.getId();
            String medicationIdStr = medicationId.toString();
            map.put("id", medicationIdStr);
            map.put("name", medication.getName());
            map.put("price", medication.getPrice());
            map.put("stock", medication.getStock());
            map.put("image", medication.getImage());
            result.add(map);
        }
        result_map.put("medications", result);
        result_map.put("total", total);
        return Result.success(result_map);
    }

    @Override
    public Result searchRun(HttpServletRequest request) {
        String runName = request.getParameter("runName");
        String date = request.getParameter("date");
        if (StringUtil.isNullOrEmpty(runName) && StringUtil.isNullOrEmpty(date)) {
            return Result.success();
        } else if (StringUtil.isNullOrEmpty(runName)) {
            QueryWrapper<Running> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("date", date);
            List<Running> runningList = runningMapper.selectList(queryWrapper);
            return Result.success(runningList);
        } else if (StringUtil.isNullOrEmpty(date)) {
            QueryWrapper<Running> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("name", runName);
            List<Running> runningList = runningMapper.selectList(queryWrapper);
            return Result.success(runningList);
        } else {
            QueryWrapper<Running> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("date", date)
                    .like("name", runName);
            List<Running> runningList = runningMapper.selectList(queryWrapper);
            return Result.success(runningList);
        }
    }



}
