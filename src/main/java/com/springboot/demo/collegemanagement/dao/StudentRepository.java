package com.springboot.demo.collegemanagement.dao;

import com.springboot.demo.collegemanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    public List<Student> findAllByOrderByLastNameAsc();
}
