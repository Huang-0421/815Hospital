package com.nine.backend.service;

import com.nine.backend.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface ShowInfoService {
    Result getPatientById(HttpServletRequest httpServletRequest);
    Result getPatients(HttpServletRequest httpServletRequest);
    Result getPayment(HttpServletRequest httpServletRequest);
    Result doPayment(@RequestBody Map<String, List<String>> requestBody);
    Result getRegistration(HttpServletRequest httpServletRequest);
    Result getMedical(HttpServletRequest httpServletRequest);
    Result getNoti(HttpServletRequest httpServletRequest);
    Result getPay(HttpServletRequest httpServletRequest);
    Result getDoc(HttpServletRequest httpServletRequest);
    Result pageShowPatient(HttpServletRequest request);
    Result pageShowDoctor(HttpServletRequest request);
    Result showAllDoc();
    Result pageShowFeedback(HttpServletRequest request);
    Result showMedical(HttpServletRequest httpServletRequest);
    Result pageShowRun(HttpServletRequest request);
    Result showFeedback(HttpServletRequest request);
    Result showMe(HttpServletRequest request);
    Result showAll(HttpServletRequest request);
}
