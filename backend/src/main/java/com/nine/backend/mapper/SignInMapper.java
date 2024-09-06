package com.nine.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nine.backend.pojo.Patient;

/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/19 下午8:51
 */
public interface SignInMapper extends BaseMapper<Patient> {
    //在 Login 中使用，没有单独的 controller 与 service
}
