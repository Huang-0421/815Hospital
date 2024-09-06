package com.nine.backend.service;

import com.nine.backend.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/21 下午4:37
 */
public interface PaymentService {
    Result createPayment(HttpServletRequest request);
    Result pay(@RequestBody List<Long> paymentIds);
}
