package com.nine.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nine.backend.pojo.Feedback;
import com.nine.backend.utils.Result;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/23 下午8:07
 */
public interface FeedbackService extends IService<Feedback> {
    Result createFeedback(HttpServletRequest request)throws IOException;
}
