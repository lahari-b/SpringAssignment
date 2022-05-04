package com.springboot.demo.collegemanagement.controller;

import com.springboot.demo.collegemanagement.entity.Course;
import com.springboot.demo.collegemanagement.service.course.CourseService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private CourseService courseService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){

        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/list")
    public String listCourses(Model theModel){

        List<Course> theCourses=courseService.findAll();
        theModel.addAttribute("courses",theCourses);
        return "courses/list-courses";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        Course theCourse=new Course();
        theModel.addAttribute("courses",theCourse);
        return "courses/courses-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("courseId") int theId, Model theModel){

        Course theCourse=courseService.findById(theId);
        theModel.addAttribute("courses",theCourse);
        return "courses/courses-form";
    }

    @PostMapping("save")
    public String saveCourse(@Valid @ModelAttribute("courses") Course theCourse, BindingResult theBindingResult){
        if(theBindingResult.hasErrors()){
            return "courses/courses-form";
        }
        else{
            courseService.save(theCourse);
            return "redirect:/courses/list";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("courseId") int theId){

        courseService.deleteById(theId);
        return "redirect:/courses/list";

    }
}
