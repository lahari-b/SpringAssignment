package com.springboot.demo.collegemanagement.exception.course;

public class CourseException extends RuntimeException{
    public CourseException(String message) {
        super(message);
    }

    public CourseException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseException(Throwable cause) {
        super(cause);
    }
}
