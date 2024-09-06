package com.nine.backend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nine.backend.mapper.PatientMapper;
import com.nine.backend.pojo.Patient;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements PatientService {
    // 你可以在这里实现自定义的方法
}
