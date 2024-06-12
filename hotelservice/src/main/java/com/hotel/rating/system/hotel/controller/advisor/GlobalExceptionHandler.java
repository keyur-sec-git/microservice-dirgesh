package com.hotel.rating.system.hotel.controller.advisor;

import com.hotel.rating.system.hotel.exceptions.DataAlreadyExistsException;
import com.hotel.rating.system.hotel.exceptions.DataNotFoundException;
import com.hotel.rating.system.hotel.model.response.ServiceResponseBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataAlreadyExistsException.class)
    public ResponseEntity<ServiceResponseBean> handleDataAlreadyExistsException(DataAlreadyExistsException dataAlreadyExistsException) {
        ServiceResponseBean serviceResponseBean = ServiceResponseBean.builder()
                .status(Boolean.FALSE)
                .message(dataAlreadyExistsException.getMessage())
                .build();
        return new ResponseEntity<>(serviceResponseBean, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ServiceResponseBean> handleDataNotFoundException(DataNotFoundException dataNotFoundException) {
        ServiceResponseBean serviceResponseBean = ServiceResponseBean.builder()
                .status(Boolean.FALSE)
                .message(dataNotFoundException.getMessage())
                .build();
        return new ResponseEntity<>(serviceResponseBean, HttpStatus.NOT_FOUND);
    }

}
