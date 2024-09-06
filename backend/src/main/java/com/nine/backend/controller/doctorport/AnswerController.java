package com.nine.backend.controller.doctorport;

import com.nine.backend.service.AnswerService;
import com.nine.backend.service.AskService;
import com.nine.backend.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnswerController {
    @Autowired
    private AnswerService answerService;
    @PostMapping("/doctor/answer")
    public Result answer(HttpServletRequest req) {
        return answerService.answer(req);
    }
    @GetMapping("/doctor/showinhome")
    public Result showInHome(HttpServletRequest req) {
        return answerService.showInHome(req);
    }
}
