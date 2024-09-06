package com.nine.backend.service;

import com.nine.backend.utils.Result;
import jakarta.servlet.http.HttpServletRequest;

public interface SelectWardService {
    Result selectWard(HttpServletRequest req);
    Result showFirstWard(HttpServletRequest request);
    Result showSpecWard(HttpServletRequest request);
}
