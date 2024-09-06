package com.nine.backend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nine.backend.mapper.RunningMapper;
import com.nine.backend.pojo.Running;
import org.springframework.stereotype.Service;

/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/28 上午3:44
 */
@Service
public class RunningServiceImpl extends ServiceImpl<RunningMapper, Running> implements RunningService {
}
