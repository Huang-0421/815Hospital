package com.nine.backend.controller.patientport;

import com.nine.backend.service.RegistrationService;
import com.nine.backend.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/20 下午7:07
 */
@RestController
public class RegistrationController {
    @Autowired
    private RegistrationService appointmentService;

    //预约挂号
    @PostMapping("/patient/appoint")
    public Result makeAppointment(HttpServletRequest httpServletRequest)throws Exception{
       return appointmentService.makeAppointment(httpServletRequest);
    }
}
