package com.nine.backend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.nine.backend.mapper.AskAnswerMapper;
import com.nine.backend.mapper.NotificationMapper;
import com.nine.backend.mapper.PatientMapper;
import com.nine.backend.pojo.AskAnswer;
import com.nine.backend.pojo.Notification;
import com.nine.backend.pojo.Patient;
import com.nine.backend.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnwserServiceImpl implements AnswerService {
    @Autowired
    private AskAnswerMapper askAnswerMapper;
    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public Result answer(HttpServletRequest req) {
        Long doctorId = Long.parseLong(req.getSession().getAttribute("doctorId").toString());
        Long askanswerId = Long.parseLong(req.getParameter("askanswerId"));
        String answer = req.getParameter("answer");
        AskAnswer askAnswer = new AskAnswer();
        askAnswer.setDoctorId(doctorId);
        askAnswer.setAnswer(answer);
        UpdateWrapper<AskAnswer> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", askanswerId);
        askAnswerMapper.update(askAnswer, updateWrapper);
        return Result.success();
    }

    @Override
    public Result showInHome(HttpServletRequest req) {
        Long doctorId = Long.parseLong(req.getSession().getAttribute("doctorId").toString());
        //取到老板的消息
        QueryWrapper<Notification> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("doctor_id", doctorId)
                .orderByDesc("id");
        List<Notification> notificationList = notificationMapper.selectList(queryWrapper);
        List<Map<String,Object>> result = new ArrayList<>();
        for (Notification notification : notificationList) {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("patientName", "boss");
            map1.put("image","https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/e7195fae-ca61-449f-8c47-7bfe364c6a6b.png");
            map1.put("content", notification.getContent());
            result.add(map1);
        }
        //取到患者消息
        QueryWrapper<AskAnswer> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.isNull("answer")
                .orderByDesc("id");
        List<AskAnswer> askAnswerList = askAnswerMapper.selectList(queryWrapper1);
        for (AskAnswer askAnswer : askAnswerList) {
            Map<String, Object> map = new HashMap<>();
            Long patientId = askAnswer.getPatientId();
            QueryWrapper<Patient> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("id", patientId);
            Patient patient = patientMapper.selectOne(queryWrapper2);
            map.put("patientName", patient.getUsername());
            map.put("question", askAnswer.getQuestion());
            map.put("image", patient.getPhoto());
            map.put("askanswerId", askAnswer.getId().toString());
            result.add(map);
        }
        return Result.success(result);
    }
}
