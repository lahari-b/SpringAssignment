package com.springboot.demo.collegemanagement.dao;

import com.springboot.demo.collegemanagement.entity.AdminStaff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<AdminStaff,Integer> {

    public List<AdminStaff> findAllByOrderByLastNameAsc();
}
