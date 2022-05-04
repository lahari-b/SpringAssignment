package com.springboot.demo.collegemanagement.service.student;

import com.springboot.demo.collegemanagement.entity.Student;

import java.util.List;

public interface StudentService {
    public List<Student> findAll();

    public Student findById(int theId);

    public void save(Student theStudent);

    public void deleteById(int theId);
}
