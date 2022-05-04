package com.springboot.demo.collegemanagement.service.adminstaff;

import com.springboot.demo.collegemanagement.dao.AdminRepository;
import com.springboot.demo.collegemanagement.entity.AdminStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public List<AdminStaff> findAll() {
        return adminRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public AdminStaff findById(int theId) {
        Optional<AdminStaff> result=adminRepository.findById(theId);

        AdminStaff theAdminStaff=null;

        if(result.isPresent()){
            theAdminStaff=result.get();
        }
        else{
            throw new RuntimeException("Did not find Admin Staff Id - "+theId);
        }

        return theAdminStaff;
    }

    @Override
    public void save(AdminStaff theAdminStaff) {
        adminRepository.save(theAdminStaff);
    }

    @Override
    public void deleteById(int theId) {
        adminRepository.deleteById(theId);
    }
}
