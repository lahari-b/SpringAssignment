package com.springboot.demo.collegemanagement.exception.admin;

public class AdminNotFoundException extends RuntimeException{
    public AdminNotFoundException(String message) {
        super(message);
    }

    public AdminNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdminNotFoundException(Throwable cause) {
        super(cause);
    }
}
