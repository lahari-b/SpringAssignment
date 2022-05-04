package com.springboot.demo.collegemanagement.service.student;

import com.springboot.demo.collegemanagement.dao.CourseRepository;
import com.springboot.demo.collegemanagement.dao.StudentRepository;
import com.springboot.demo.collegemanagement.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository=courseRepository;
    }

    @Override
    public List<Student> findAll() {

        return (List<Student>)(studentRepository.findAllByOrderByLastNameAsc());
    }

    @Override
    public Student findById(int theId) {
        Optional<Student> result=studentRepository.findById(theId);

        Student theStudent=null;

        if(result.isPresent()){
            theStudent=result.get();
        }
        else{
            throw new RuntimeException("Did not find Student Id - "+theId);
        }

        return theStudent;
    }

    @Override
    public void save(Student theStudent) {
        studentRepository.save(theStudent);
    }

    @Override
    public void deleteById(int theId) {
        studentRepository.deleteById(theId);
    }
}
