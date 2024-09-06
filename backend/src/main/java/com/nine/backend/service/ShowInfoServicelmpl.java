package com.nine.backend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nine.backend.mapper.*;
import com.nine.backend.pojo.*;
import com.nine.backend.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.RestClientAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShowInfoServicelmpl implements ShowInfoService{

    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private RegistrationMapper registrationMapper;
    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private MedicationMapper medicationMapper;
    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private MedicationService medicationService;
    @Autowired
    private RunningService runningService;
    @Autowired
    private FeedbackMapper feedbackMapper;

    //展示患者个人信息
    @Override
    public Result getPatientById(HttpServletRequest httpServletRequest) {
        Long id = Long.parseLong(httpServletRequest.getSession().getAttribute("patientId").toString());
        //找到当前患者
        QueryWrapper<Patient> queryWrapper_01 = new QueryWrapper<>();
        queryWrapper_01.eq("id",id);
        Patient patient = patientMapper.selectOne(queryWrapper_01);
        //找到最新的挂号
        Long registrationId = patient.getRegistrationId();
        Map<String, Object> response = new HashMap<>();
        if(registrationId != null){
            QueryWrapper<Registration> queryWrapper_02 = new QueryWrapper<>();
            queryWrapper_02.eq("id",registrationId);
            Registration registration = registrationMapper.selectOne(queryWrapper_02);
            String inquiryTime = registration.getInquiryTime();
            response.put("patient", patient);
            response.put("inquiryTime", inquiryTime);
            System.out.println(response);
            return Result.success(response);
        }
        else{
            response.put("patient", patient);
            response.put("inquiryTime", "暂无挂号信息");
            return Result.success(response);
        }
    }
    //展示患者个人缴费单
    @Override
    public Result getPayment(HttpServletRequest httpServletRequest) {
        Long patientId = Long.parseLong(httpServletRequest.getSession().getAttribute("patientId").toString());
        QueryWrapper<Payment> queryWrapper_01 = new QueryWrapper<>();
        queryWrapper_01.eq("patient_id",patientId);
        List<Payment> payments = paymentMapper.selectList(queryWrapper_01);
        List<Map<String, Object>> result = new ArrayList<>();
        for(Payment payment : payments){
            Map<String, Object> map = new HashMap<>();
            map.put("id",payment.getId().toString());
            map.put("medicationName",payment.getMedicationName());
            map.put("quantity",payment.getQuantity());
            map.put("amountDue",payment.getAmountDue());
            map.put("price",payment.getPrice());
            map.put("flag",payment.getFlag());
            result.add(map);
        }
        return Result.success(result);
    }

    @Override
    public Result doPayment(@RequestBody Map<String, List<String>> requestBody) {
        List<String> paymentIdList = requestBody.get("ids");
        for(String paymentIdStr : paymentIdList) {
            Long paymentId = Long.parseLong(paymentIdStr);
            QueryWrapper<Payment> queryWrapper_01 = new QueryWrapper<>();
            queryWrapper_01.eq("id", paymentId);
            Payment payment = paymentMapper.selectOne(queryWrapper_01);
            payment.setId(paymentId);
            payment.setFlag(1);
            paymentMapper.updateById(payment);
        }
        return Result.success();
    }

    //展示患者个人挂号单
    @Override
    public Result getRegistration(HttpServletRequest httpServletRequest) {
        Long patientId = Long.parseLong(httpServletRequest.getSession().getAttribute("patientId").toString());
        QueryWrapper<Registration> queryWrapper_01 = new QueryWrapper<>();
        queryWrapper_01.eq("patient_id",patientId)
                .orderByDesc("id");
        List<Registration> registrations = registrationMapper.selectList(queryWrapper_01);
        if(registrations.isEmpty()){
            return Result.error("暂无挂号信息");
        }
        return Result.success(registrations);
    }

    @Override
    public Result getMedical(HttpServletRequest httpServletRequest) {

        List<Medication> medicationList = medicationMapper.selectList(null);
        return Result.success(medicationList);
    }

    @Override
    public Result getNoti(HttpServletRequest httpServletRequest) {
        Long doctorId = Long.parseLong(httpServletRequest.getSession().getAttribute("doctorId").toString());
        QueryWrapper<Notification> queryWrapper_01 = new QueryWrapper<>();
        queryWrapper_01.eq("doctor_id",doctorId);
        List<Notification> notifications = notificationMapper.selectList(queryWrapper_01);
        if(notifications.isEmpty()) return Result.error("暂无通知");
        return Result.success(notifications);
    }

    @Override
    public Result getPay(HttpServletRequest httpServletRequest) {
        Long doctorId = Long.parseLong(httpServletRequest.getSession().getAttribute("doctorId").toString());
        QueryWrapper<Payment> queryWrapper_01 = new QueryWrapper<>();
        queryWrapper_01.eq("doctor_id",doctorId);
        List<Payment> payments = paymentMapper.selectList(queryWrapper_01);
        return Result.success(payments);
    }

    @Override
    public Result getDoc(HttpServletRequest httpServletRequest) {
        List<Map<String, Object>> result = new ArrayList<>();
        QueryWrapper<Doctor> queryWrapper_01 = new QueryWrapper<>();
        queryWrapper_01.eq("duty", 1);
        List<Doctor> doctorList = doctorMapper.selectList(queryWrapper_01);
        for(Doctor doctor : doctorList){
            Map<String, Object> map = new HashMap<>();
            Long departmentId = doctor.getDepartmentId();
            QueryWrapper<Department> queryWrapper_02 = new QueryWrapper<>();
            queryWrapper_02.eq("id",departmentId);
            Department department = departmentMapper.selectOne(queryWrapper_02);
            map.put("departmentName",department.getName());
            map.put("doctorName",doctor.getName());
            result.add(map);
        }
        return Result.success(result);
    }

    @Override
    public Result pageShowPatient(HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        Page<Patient> page1 = Page.of(page, pageSize);
        page1 = patientService.page(page1);
        List<Patient> records = page1.getRecords();
        long total = page1.getTotal();
        Map<String, Object> result = new HashMap<>();
        result.put("records", records);
        result.put("total", total);
        return Result.success(result);
    }

    @Override
    public Result pageShowDoctor(HttpServletRequest request) {
        String pagestr = request.getParameter("page");
        String pageSizestr = request.getParameter("pageSize");
        int page = Integer.parseInt(pagestr);
        int pageSize = Integer.parseInt(pageSizestr);
        Page<Doctor> page1 = Page.of(page, pageSize);
        page1 = doctorService.page(page1);
        List<Doctor> records = page1.getRecords();
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> result_map = new HashMap<>();
        long total = page1.getTotal();
        for (Doctor doctor : records) {
            Map<String, Object> map = new HashMap<>();
            Long doctorId = doctor.getId();
            String doctorIdStr = doctorId.toString();
            map.put("id", doctorIdStr);
            map.put("name", doctor.getName());
            map.put("phone", doctor.getPhone());
            map.put("age", doctor.getAge());
            map.put("gender", doctor.getGender());
            map.put("departmentId", doctor.getDepartmentId());
            map.put("duty",doctor.getDuty());
            result.add(map);
        }
        result_map.put("doctors", result);
        result_map.put("total", total);
        return Result.success(result_map);
    }

    @Override
    public Result showAllDoc() {
        List<Doctor> doctorList = doctorMapper.selectList(null);
        List<String> doctorNameList = new ArrayList<>();
        for (Doctor doctor : doctorList) {
            doctorNameList.add(doctor.getName());
        }
        return Result.success(doctorNameList);
    }

    @Override
    public Result pageShowFeedback(HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        Page<Feedback> page1 = Page.of(page, pageSize);
        QueryWrapper<Feedback> queryWrapper_01 = new QueryWrapper<>();
        queryWrapper_01.orderByDesc("id");
        page1 = feedbackService.page(page1,queryWrapper_01);
        List<Feedback> records = page1.getRecords();
        long total = page1.getTotal();
        Map<String, Object> result = new HashMap<>();
        result.put("records", records);
        result.put("total", total);
        return Result.success(result);
    }

    @Override
    public Result showMedical(HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        Page<Medication> page1 = Page.of(page, pageSize);
        page1 = medicationService.page(page1);
        List<Medication> records = page1.getRecords();
        long total = page1.getTotal();
        Map<String, Object> result = new HashMap<>();
        result.put("records", records);
        result.put("total", total);
        return Result.success(result);
    }

    @Override
    public Result pageShowRun(HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        Page<Running> page1 = Page.of(page, pageSize);
        page1 = runningService.page(page1);
        List<Running> records = page1.getRecords();
        long total = page1.getTotal();
        Map<String, Object> result = new HashMap<>();
        result.put("records", records);
        result.put("total", total);
        return Result.success(result);
    }

    @Override
    public Result showFeedback(HttpServletRequest request) {
        Long patientId = Long.parseLong(request.getSession().getAttribute("patientId").toString());
        QueryWrapper<Feedback> queryWrapper_01 = new QueryWrapper<>();
        queryWrapper_01.eq("patient_id", patientId)
                .orderByDesc("id")
                .last("LIMIT 2");
        List<Feedback> feedbackList = feedbackMapper.selectList(queryWrapper_01);
        return Result.success(feedbackList);
    }

    @Override
    public Result showMe(HttpServletRequest request) {
        Long doctorId = Long.parseLong(request.getSession().getAttribute("doctorId").toString());
        QueryWrapper<Doctor> queryWrapper_01 = new QueryWrapper<>();
        queryWrapper_01.eq("id", doctorId);
        Doctor doctor = doctorMapper.selectOne(queryWrapper_01);
        Map<String, Object> result = new HashMap<>();
        result.put("name", doctor.getName());
        result.put("id", doctorId );
        result.put("phone", doctor.getPhone());
        result.put("image",doctor.getImage());
        QueryWrapper<Registration> queryWrapper_02 = new QueryWrapper<>();
        queryWrapper_02.eq("doctor_id", doctorId)
                .ne("flag", 1);
        Long todo = registrationMapper.selectCount(queryWrapper_02);
        result.put("todo", todo);
        return Result.success(result);
    }

    @Override
    public Result showAll(HttpServletRequest request) {
        QueryWrapper<Doctor> queryWrapper_01 = new QueryWrapper<>();
        queryWrapper_01.eq("duty",1);
        Long dutyCount = doctorMapper.selectCount(queryWrapper_01);
        Long count = doctorMapper.selectCount(null);
        String rate = String.format("%.2f%%", (double) dutyCount / count * 100);
        Map<String, Object> result = new HashMap<>();
        result.put("count", dutyCount);
        result.put("rate", rate);
        return Result.success(result);
    }


    //展示医生所有病人
    @Override
    public Result getPatients(HttpServletRequest httpServletRequest) {
        Long doctorId = Long.parseLong(httpServletRequest.getSession().getAttribute("doctorId").toString());
        QueryWrapper<Registration> queryWrapper_01 = new QueryWrapper<>();
        queryWrapper_01.eq("doctor_id",doctorId);
        List<Registration> registrations = registrationMapper.selectList(queryWrapper_01);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Registration registration : registrations) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", registration.getId().toString());
            Long patientId = registration.getPatientId();
            QueryWrapper<Patient> queryWrapper_02 = new QueryWrapper<>();
            queryWrapper_02.eq("id", patientId);
            Patient patient = patientMapper.selectOne(queryWrapper_02);
            map.put("phone", patient.getPhone());
            map.put("name",patient.getUsername());
            map.put("inquiryTime",registration.getInquiryTime());
            result.add(map);
        }
        return Result.success(result);
    }
}