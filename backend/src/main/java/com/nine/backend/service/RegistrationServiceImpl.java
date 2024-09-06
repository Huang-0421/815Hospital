package com.nine.backend.service;

import com.aliyun.oss.model.LiveChannelListing;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.nine.backend.mapper.PatientMapper;
import com.nine.backend.mapper.RegistrationMapper;
import com.nine.backend.mapper.DoctorMapper;
import com.nine.backend.pojo.Doctor;
import com.nine.backend.pojo.Patient;
import com.nine.backend.pojo.Registration;
import com.nine.backend.utils.Result;
import com.nine.backend.utils.SendMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/20 下午7:18
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private RegistrationMapper registrationMapper;
    @Autowired
    private PatientMapper patientMapper;

    @Override
    public Result makeAppointment(HttpServletRequest request) throws Exception {
        String doctorName = request.getParameter("doctorName");
        String department = request.getParameter("department");
        String dateString = request.getParameter("date");//前端返回患者预约的时间
        Long patientId = Long.parseLong(request.getSession().getAttribute("patientId").toString());

        // 解析 dateString
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime date = LocalDateTime.parse(dateString, inputFormatter);
        // 将日期和时间格式化
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
        String formattedDate = date.format(outputFormatter);

        //查找医生
        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", doctorName);
        Doctor doctor = doctorMapper.selectOne(queryWrapper);
        if (doctor == null) {
            return Result.error("未关联到该医生");
        }
        //查找并更新该用户信息（关联挂号单）
        QueryWrapper<Patient> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("id", patientId);
        Patient patient = patientMapper.selectOne(queryWrapper2);
        if (patient == null) {
            return Result.error("未关联到患者");
        }
        //创建挂号单
        Registration registration = new Registration();
        registration.setDoctorName(doctorName);
        registration.setPatientId(patientId);
        registration.setDoctorId(doctor.getId());
        registration.setFee(doctor.getFee());
        registration.setInquiryTime(formattedDate);
        registration.setDepartment(department);
        registration.setPatientName(patient.getUsername());
        registrationMapper.insert(registration);
        Long registrationId = registration.getId();

        patient.setRegistrationId(registrationId);
        patientMapper.updateById(patient);

        // 生成4位随机验证码
        Random random = new Random();
        String code = String.valueOf(1000 + random.nextInt(9000));
        // 构造 templateParam 参数，包含生成的验证码和格式化后的日期
        String templateParam = String.format("{\"code\":\"%s\", \"date\":\"%s\"}", code, formattedDate);
        // 创建客户端并配置发送短信请求
        com.aliyun.dysmsapi20170525.Client client = SendMessage.createClient();
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setSignName("八一五医院")
                .setTemplateCode("SMS_471925108")
                .setPhoneNumbers(patient.getPhone())
                .setTemplateParam(templateParam);
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        //发送通知
        client.sendSmsWithOptions(sendSmsRequest, runtime);
        return Result.success();
    }
}
