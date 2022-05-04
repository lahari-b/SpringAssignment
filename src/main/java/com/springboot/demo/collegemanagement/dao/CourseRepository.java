package com.springboot.demo.collegemanagement.dao;

import com.springboot.demo.collegemanagement.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Integer> {

    public List<Course> findAllByOrderByTitleAsc();
}
