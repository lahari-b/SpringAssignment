package com.springboot.demo.collegemanagement.service.adminstaff;

import com.springboot.demo.collegemanagement.dao.AdminRepository;
import com.springboot.demo.collegemanagement.entity.AdminStaff;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
class AdminServiceImplTest {

    private AdminRepository adminRepository;
    private AdminServiceImpl adminService;

    @Test
    void findAll() {

        adminRepository=mock(AdminRepository.class);
        adminService=new AdminServiceImpl(adminRepository);

        List<AdminStaff> adminList = new ArrayList<>();
        adminList.add(new AdminStaff(101,"Abhishek","Shetty","abhi@gmail.com"));
        adminList.add(new AdminStaff(102,"Harshita","Sharma","harshita@yahoo.co.in"));


        when(adminRepository.findAllByOrderByLastNameAsc()).thenReturn(adminList);

        List<AdminStaff> expectedList = adminService.findAll();

        assertEquals(2, expectedList.size());

    }

    @Test
    void findById() {
        adminRepository=mock(AdminRepository.class);
        adminService=new AdminServiceImpl(adminRepository);

        AdminStaff adminStaff=new AdminStaff(101,"Lucky","Singh","luckt@gmail.com");

        when(adminRepository.findById(101)).thenReturn(Optional.of(adminStaff));

        AdminStaff adminStaff1=adminService.findById(101);

        Assertions.assertThat(adminStaff1.getId()).isEqualTo(101);
        Assertions.assertThat(adminStaff1.getFirstName()).isEqualTo("Lucky");
        Assertions.assertThat(adminStaff1.getLastName()).isEqualTo("Singh");
        Assertions.assertThat(adminStaff1.getEmail()).isEqualTo("luckt@gmail.com");
        verify(adminRepository,times(1)).findById(101);
    }

    @Test
    void save() {
        adminRepository=mock(AdminRepository.class);
        adminService=new AdminServiceImpl(adminRepository);

        AdminStaff adminStaff=new AdminStaff(101,"Lucky","Singh","luckt@gmail.com");

        adminService.save(adminStaff);
        verify(adminRepository).save(adminStaff);
    }

    @Test
    void deleteById() {
        adminRepository=mock(AdminRepository.class);
        adminService=new AdminServiceImpl(adminRepository);

        adminService.deleteById(101);
        verify(adminRepository).deleteById(101);
    }
}