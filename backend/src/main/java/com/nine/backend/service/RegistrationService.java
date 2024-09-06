package com.nine.backend.service;

import com.nine.backend.utils.Result;
import jakarta.servlet.http.HttpServletRequest;


/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/20 下午7:16
 */
public interface RegistrationService {
   Result makeAppointment(HttpServletRequest httpServletRequest) throws Exception;
}
