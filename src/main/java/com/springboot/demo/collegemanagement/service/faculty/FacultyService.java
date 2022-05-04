package com.springboot.demo.collegemanagement.service.faculty;

import com.springboot.demo.collegemanagement.entity.Faculty;

import java.util.List;

public interface FacultyService {
    public List<Faculty> findAll();

    public Faculty findById(int theId);

    public void save(Faculty theFaculty);

    public void deleteById(int theId);
}
