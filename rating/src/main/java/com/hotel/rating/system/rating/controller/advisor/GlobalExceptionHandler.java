package com.hotel.rating.system.rating.controller.advisor;

import com.hotel.rating.system.rating.exception.DataNotFoundException;
import com.hotel.rating.system.rating.model.ResponseBean.ServiceResponseBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ServiceResponseBean> handleDataNotFoundException(DataNotFoundException e) {
        ServiceResponseBean serviceResponseBean = ServiceResponseBean.builder()
                .status(Boolean.FALSE)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(serviceResponseBean, HttpStatus.NOT_FOUND);
    }

}
