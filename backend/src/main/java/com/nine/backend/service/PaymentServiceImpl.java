package com.nine.backend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.nine.backend.mapper.MedicationMapper;
import com.nine.backend.mapper.PatientMapper;
import com.nine.backend.mapper.PaymentMapper;
import com.nine.backend.mapper.RegistrationMapper;
import com.nine.backend.pojo.Medication;
import com.nine.backend.pojo.Patient;
import com.nine.backend.pojo.Payment;
import com.nine.backend.pojo.Registration;
import com.nine.backend.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/21 下午4:37
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private MedicationMapper medicationMapper;
    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private RegistrationMapper registrationMapper;

    @Override
    public Result createPayment(HttpServletRequest request) {
        Long doctorId = Long.parseLong(request.getSession().getAttribute("doctorId").toString());
        Long medicationId = Long.parseLong(request.getParameter("medicationId"));
        Long registrationId = Long.parseLong(request.getParameter("patientId"));
        QueryWrapper<Registration> registrationQueryWrapper = new QueryWrapper<>();
        registrationQueryWrapper.eq("id", registrationId);
        Registration registration = registrationMapper.selectOne(registrationQueryWrapper);
        registration.setFlag(1);
        registrationMapper.updateById(registration);
        Long patientId = registration.getPatientId();
        String diagnosis = request.getParameter("diagnosis");//诊断意见
        Integer quantity = Integer.parseInt(request.getParameter("quantity"));
        QueryWrapper<Medication> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", medicationId);
        Medication medication = medicationMapper.selectOne(queryWrapper);
        QueryWrapper<Patient> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("id", patientId);
        Patient patient = patientMapper.selectOne(queryWrapper1);
        Payment payment = new Payment();
        payment.setDoctorId(doctorId);
        payment.setPatientId(patientId);
        payment.setDiagnosis(diagnosis);
        payment.setQuantity(quantity);
        payment.setMedicationName(medication.getName());
        payment.setPatientName(patient.getUsername());
        payment.setPrice(medication.getPrice());
        int amountDue = payment.getQuantity() * payment.getPrice();
        payment.setAmountDue(amountDue);
        paymentMapper.insert(payment);
        return Result.success();
    }
    @Override
    public Result pay(@RequestBody List<Long> paymentIds) {
        for (Long paymentId : paymentIds) {
            UpdateWrapper<Payment> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", paymentId);
            Payment payment = new Payment();
            payment.setFlag(1);
            paymentMapper.update(payment,updateWrapper);
        }
        return Result.success();
    }
}
