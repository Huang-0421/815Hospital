package com.nine.backend.service;

import com.nine.backend.utils.Result;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/8/22 下午7:21
 */
public interface SettingService {
    Result setPatientInfo(HttpServletRequest request);
    Result showReady(HttpServletRequest request);
    Result setDocinfo(HttpServletRequest request) throws IOException;
}
