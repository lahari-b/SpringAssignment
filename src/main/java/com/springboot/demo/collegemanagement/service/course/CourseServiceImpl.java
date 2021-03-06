package com.springboot.demo.collegemanagement.service.course;

import com.springboot.demo.collegemanagement.dao.CourseRepository;
import com.springboot.demo.collegemanagement.entity.Course;
import com.springboot.demo.collegemanagement.exception.course.CourseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAllByOrderByTitleAsc();
    }

    @Override
    public Course findById(int theId) {
        Optional<Course> result=courseRepository.findById(theId);

        Course theCourse=null;

        if(result.isPresent()){
            theCourse=result.get();
        }
        else{
            throw new CourseException("Did not find Course Id - "+theId);
        }
        return theCourse;
    }

    @Override
    public Course save(Course theCourse) {
        return courseRepository.save(theCourse);
    }

    @Override
    public void deleteById(int theId) {
        courseRepository.deleteById(theId);
    }
}
