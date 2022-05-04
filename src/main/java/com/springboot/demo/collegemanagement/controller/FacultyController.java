package com.springboot.demo.collegemanagement.controller;

import com.springboot.demo.collegemanagement.entity.Faculty;
import com.springboot.demo.collegemanagement.service.faculty.FacultyService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/faculty")
public class FacultyController {

    private FacultyService facultyService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){

        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/list")
    public String listfaculty(Model theModel){

        List<Faculty> theFaculty=facultyService.findAll();

        theModel.addAttribute("faculty",theFaculty);
        return "faculty/list-faculty";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        Faculty theFaculty=new Faculty();
        theModel.addAttribute("faculty",theFaculty);
        return "faculty/faculty-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("facultyId") int theId, Model theModel){

        Faculty thefaculty=facultyService.findById(theId);
        theModel.addAttribute("faculty",thefaculty);
        return "faculty/faculty-form";
    }

    @PostMapping("save")
    public String savefaculty(@Valid @ModelAttribute("faculty") Faculty theFaculty, BindingResult theBindingResult){
        if(theBindingResult.hasErrors()){
            return "faculty/faculty-form";
        }
        else{
            facultyService.save(theFaculty);
            return "redirect:/faculty/list";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("facultyId") int theId){

        facultyService.deleteById(theId);
        return "redirect:/faculty/list";

    }
}
