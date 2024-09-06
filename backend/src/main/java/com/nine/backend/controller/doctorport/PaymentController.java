package com.nine.backend.controller.doctorport;

import com.nine.backend.service.PaymentService;
import com.nine.backend.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/21 下午4:29
 */
@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    //医生建立payment缴费单
    @PostMapping("/doctor/payment")
    public Result createPament(HttpServletRequest request) {
        return paymentService.createPayment(request);
    }
    //患者缴费
    @PostMapping("/patient/pay")
    public Result pay(@RequestBody List<Long> paymentIds){
        return paymentService.pay(paymentIds);
    }
}
