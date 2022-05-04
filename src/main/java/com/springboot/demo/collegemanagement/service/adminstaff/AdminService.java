package com.springboot.demo.collegemanagement.service.adminstaff;

import com.springboot.demo.collegemanagement.entity.AdminStaff;

import java.util.List;

public interface AdminService {
    public List<AdminStaff> findAll();

    public AdminStaff findById(int theId);

    public void save(AdminStaff theAdminStaff);

    public void deleteById(int theId);
}
