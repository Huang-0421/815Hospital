package com.nine.backend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nine.backend.mapper.FeedbackMapper;
import com.nine.backend.mapper.PatientMapper;
import com.nine.backend.pojo.Feedback;
import com.nine.backend.pojo.Patient;
import com.nine.backend.utils.AliOSSUtils;
import com.nine.backend.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/23 下午8:07
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {
    @Autowired
    private FeedbackMapper feedbackMapper;
    @Autowired
    private AliOSSUtils aliOSSUtils;
    @Override
    public Result createFeedback(HttpServletRequest request) throws IOException {
        Long patientId = Long.parseLong(request.getSession().getAttribute("patientId").toString());
        String patientName = request.getParameter("patientName");
        String createTime = request.getParameter("createTime");
        String phone = request.getParameter("phone");
        String type = request.getParameter("type");
        String content = request.getParameter("content");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile image = multipartRequest.getFile("image");
        String url = null;
        if (image != null) {
            url = aliOSSUtils.upload(image);
        }
        Feedback feedback = new Feedback();
        feedback.setPatientName(patientName);
        feedback.setCreateTime(createTime);
        feedback.setPhone(phone);
        feedback.setType(type);
        feedback.setImage(url);
        feedback.setContent(content);
        feedback.setPatientId(patientId);
        feedbackMapper.insert(feedback);
        return Result.success();
    }
}
