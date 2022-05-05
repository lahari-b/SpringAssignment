package com.springboot.demo.collegemanagement.service.course;

import com.springboot.demo.collegemanagement.entity.Course;

import java.util.List;

public interface CourseService {
    public List<Course> findAll();

    public Course findById(int theId);

    public Course save(Course theCourse);

    public void deleteById(int theId);
}
