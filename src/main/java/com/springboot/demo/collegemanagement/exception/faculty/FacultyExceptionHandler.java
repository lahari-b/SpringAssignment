package com.springboot.demo.collegemanagement.exception.faculty;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FacultyExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<FacultyErrorResponse> handleException(Exception exc){

        FacultyErrorResponse error = new FacultyErrorResponse(HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),System.currentTimeMillis());

        return new ResponseEntity<FacultyErrorResponse>(error,HttpStatus.BAD_REQUEST);
    }
}
