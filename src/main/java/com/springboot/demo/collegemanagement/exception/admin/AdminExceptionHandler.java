package com.springboot.demo.collegemanagement.exception.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdminExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<AdminErrorResponse> handleException(Exception exc){

        AdminErrorResponse error = new AdminErrorResponse(HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),System.currentTimeMillis());

        return new ResponseEntity<AdminErrorResponse>(error,HttpStatus.BAD_REQUEST);
    }
}
