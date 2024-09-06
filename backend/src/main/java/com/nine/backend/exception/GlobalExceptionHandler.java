package com.nine.backend.exception;

import com.nine.backend.utils.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/7/22 14:45
 */
@SuppressWarnings("all")
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result ex(Exception exception){
        exception.printStackTrace();
        return Result.error("抛给全局的未知错误");
    }
}
