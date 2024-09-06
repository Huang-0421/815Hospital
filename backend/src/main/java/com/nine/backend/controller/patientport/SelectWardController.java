package com.nine.backend.controller.patientport;

import com.nine.backend.service.SelectWardService;
import com.nine.backend.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SelectWardController {
    @Autowired
    private SelectWardService selectWardService;
    @GetMapping("/patient/showfirstward")
    public Result showFirstWard(HttpServletRequest request) {
        return selectWardService.showFirstWard(request);
    }
    @PostMapping("/patient/showspecward")
    public Result showSpecWard(HttpServletRequest request) {
        return selectWardService.showSpecWard(request);
    }
    @PostMapping("/patient/selectward")
    public Result selectWard(HttpServletRequest req2) {
        return selectWardService.selectWard(req2);
    }

}
