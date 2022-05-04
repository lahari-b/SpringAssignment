package com.springboot.demo.collegemanagement.controller;

import com.springboot.demo.collegemanagement.dao.CourseRepository;
import com.springboot.demo.collegemanagement.dao.StudentRepository;
import com.springboot.demo.collegemanagement.entity.Course;
import com.springboot.demo.collegemanagement.entity.Student;
import com.springboot.demo.collegemanagement.service.course.CourseService;
import com.springboot.demo.collegemanagement.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    private CourseService courseService;

    @Autowired
    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){

        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/list")
    public String listStudents(Model theModel){

        List<Student> theStudents=studentService.findAll();

        theModel.addAttribute("students",theStudents);
        return "students/list-students";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        List<Course> theCourses=courseService.findAll();
        theModel.addAttribute("courses",theCourses);

        Student theStudent=new Student();
        theModel.addAttribute("student",theStudent);
        return "students/student-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("studentId") int theId, Model theModel){
        List<Course> theCourses=courseService.findAll();
        theModel.addAttribute("courses",theCourses);

        Student theStudent=studentService.findById(theId);
        theModel.addAttribute("student",theStudent);
        return "students/student-form";
    }

    @PostMapping("save")
    public String saveStudent(@Valid @ModelAttribute("student") Student theStudent, BindingResult theBindingResult){
        if(theBindingResult.hasErrors()){
            return "students/student-form";
        }
        else{
            studentService.save(theStudent);
            return "redirect:/students/list";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("studentId") int theId){

        studentService.deleteById(theId);
        return "redirect:/students/list";

    }
}
