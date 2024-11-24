package com.example.TaskManagement.utils;

import com.example.TaskManagement.exceptions.BusinessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(BusinessException.class)
    public BaseRespone handleException(BusinessException ex) {
        return BaseRespone.builder()
                .Data(ex.getMessage())
                .build();
    }
}
