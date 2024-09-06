package com.nine.backend.service;

import com.nine.backend.utils.Result;
import jakarta.servlet.http.HttpServletRequest;

public interface AnswerService {
    Result answer(HttpServletRequest req);
    Result showInHome(HttpServletRequest req);
}
