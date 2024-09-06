package com.nine.backend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nine.backend.mapper.DoctorMapper;
import com.nine.backend.pojo.Doctor;
import org.springframework.stereotype.Service;


/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/26 下午9:04
 */
@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor>  implements DoctorService{
}
