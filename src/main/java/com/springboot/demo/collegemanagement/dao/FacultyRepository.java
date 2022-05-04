package com.springboot.demo.collegemanagement.dao;

import com.springboot.demo.collegemanagement.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty,Integer> {

    public List<Faculty> findAllByOrderByLastNameAsc();
}
