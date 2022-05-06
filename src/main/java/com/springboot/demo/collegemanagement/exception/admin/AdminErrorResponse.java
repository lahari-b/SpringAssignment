package com.springboot.demo.collegemanagement.exception.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminErrorResponse {
    private int status;
    private String message;
    private long timeStamp;
}
