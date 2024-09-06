package com.nine.backend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nine.backend.mapper.AskAnswerMapper;
import com.nine.backend.pojo.AskAnswer;
import com.nine.backend.pojo.Patient;
import com.nine.backend.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AskServiceImpl implements AskService {
    @Autowired
    AskAnswerMapper askAnswerMapper;
    public Result ask(HttpServletRequest req){
        Long patientId = Long.parseLong(req.getSession().getAttribute("patientId").toString());
        String question = req.getParameter("question");
        AskAnswer askAnswer = new AskAnswer();
        askAnswer.setQuestion(question);
        askAnswer.setPatientId(patientId);
        askAnswerMapper.insert(askAnswer);
        return Result.success();
    }

    @Override
    //展示该患者提的所有问题
    public Result show(HttpServletRequest req) {
        Long patientId = Long.parseLong(req.getSession().getAttribute("patientId").toString());
        Map<String,Object> map = new HashMap<>();
        QueryWrapper<AskAnswer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("patient_id", patientId)
                .orderByDesc("id")  // 按照id字段降序排序
                .last("LIMIT 1");

        AskAnswer askAnswer1 = askAnswerMapper.selectOne(queryWrapper);
        QueryWrapper<AskAnswer> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("patient_id", patientId)
                .orderByDesc("id")  // 按照id字段降序排序
                .last("LIMIT 1 OFFSET 1");  // 跳过第一条记录，获取第二条记录
        AskAnswer askAnswer2 = askAnswerMapper.selectOne(queryWrapper1);
        map.put("askAnswer1",askAnswer1);
        map.put("askAnswer2",askAnswer2);
        return Result.success(map);
    }
}