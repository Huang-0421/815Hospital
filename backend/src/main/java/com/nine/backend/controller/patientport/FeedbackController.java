package com.nine.backend.controller.patientport;

import com.nine.backend.service.FeedbackService;
import com.nine.backend.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/23 下午8:04
 */
@RestController
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;
    @PostMapping("/patient/feedback")
    public Result createFeedback(HttpServletRequest request) throws IOException {
        return feedbackService.createFeedback(request);
    }
}
