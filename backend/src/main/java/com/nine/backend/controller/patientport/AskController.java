package com.nine.backend.controller.patientport;

import com.nine.backend.service.AskService;
import com.nine.backend.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AskController {
    @Autowired
    private AskService askService;

    @PostMapping("/patient/ask")
    public Result ask(HttpServletRequest req) {
        return askService.ask(req);
    }

    @GetMapping("/patient/showqst")
    public Result show(HttpServletRequest req) {
        return askService.show(req);
    }
}
