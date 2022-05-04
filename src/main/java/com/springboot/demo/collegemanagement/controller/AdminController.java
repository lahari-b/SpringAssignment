package com.springboot.demo.collegemanagement.controller;

import com.springboot.demo.collegemanagement.entity.AdminStaff;
import com.springboot.demo.collegemanagement.service.adminstaff.AdminService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/adminStaff")
public class AdminController {

    private AdminService adminService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){

        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/list")
    public String listStudents(Model theModel){

        List<AdminStaff> theAdminStaff=adminService.findAll();

        theModel.addAttribute("adminStaff",theAdminStaff);
        return "adminStaff/list-adminStaff";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        AdminStaff theAdminStaff=new AdminStaff();
        theModel.addAttribute("adminStaff",theAdminStaff);
        return "adminStaff/adminStaff-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("adminStaffId") int theId, Model theModel){

        AdminStaff theAdminStaff=adminService.findById(theId);
        theModel.addAttribute("adminStaff",theAdminStaff);
        return "adminStaff/adminStaff-form";
    }

    @PostMapping("save")
    public String saveStudent(@Valid @ModelAttribute("adminStaff") AdminStaff theAdminStaff, BindingResult theBindingResult){
        if(theBindingResult.hasErrors()){
            return "adminStaff/adminStaff-form";
        }
        else{
            adminService.save(theAdminStaff);
            return "redirect:/adminStaff/list";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("adminStaffId") int theId){

        adminService.deleteById(theId);
        return "redirect:/adminStaff/list";

    }
}
