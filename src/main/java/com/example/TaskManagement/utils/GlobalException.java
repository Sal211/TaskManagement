package com.example.TaskManagement.utils;

import com.example.TaskManagement.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(BusinessException.class)
    public BaseRespone handleException(BusinessException ex) {
        return BaseRespone.builder()
                .Data(ex.getMessage())
                .build();
    }
}
