package com.hotel.rating.system.user.controller.advice;

import com.hotel.rating.system.user.exceptions.DataAlreadyExistsException;
import com.hotel.rating.system.user.exceptions.DataNotFoundException;
import com.hotel.rating.system.user.model.ResponseBean.ServiceResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ServiceResponseBean> handleDataNotFoundException(DataNotFoundException dataNotFoundException) {
        ServiceResponseBean serviceResponseBean = ServiceResponseBean.builder()
                .status(Boolean.FALSE)
                .message(dataNotFoundException.getMessage())
                .build();
        log.error("Data not found");
        return new ResponseEntity<>(serviceResponseBean, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataAlreadyExistsException.class)
    public ResponseEntity<ServiceResponseBean> handleDataAlreadyExistsException(DataAlreadyExistsException dataAlreadyExistsException) {
        ServiceResponseBean serviceResponseBean = ServiceResponseBean.builder()
                .status(Boolean.FALSE)
                .message(dataAlreadyExistsException.getMessage())
                .build();
        log.error("Data already found");
        return new ResponseEntity<>(serviceResponseBean, HttpStatus.CONFLICT);
    }
}
