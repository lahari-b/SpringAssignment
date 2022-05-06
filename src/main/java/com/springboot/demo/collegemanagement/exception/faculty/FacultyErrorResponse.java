package com.springboot.demo.collegemanagement.exception.faculty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacultyErrorResponse {
    private int status;
    private String message;
    private long timeStamp;
}
