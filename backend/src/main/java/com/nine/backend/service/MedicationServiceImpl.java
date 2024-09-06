package com.nine.backend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nine.backend.mapper.MedicationMapper;
import com.nine.backend.pojo.Medication;
import org.springframework.stereotype.Service;

/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/28 上午2:14
 */
@Service
public class MedicationServiceImpl extends ServiceImpl<MedicationMapper, Medication> implements MedicationService {
}
