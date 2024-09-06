package com.nine.backend.service;

import com.nine.backend.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestParam;

public interface AskService {
    Result ask(HttpServletRequest req);
    Result show(HttpServletRequest req);
}
