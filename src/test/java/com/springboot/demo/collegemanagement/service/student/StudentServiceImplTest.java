package com.springboot.demo.collegemanagement.service.student;

import com.springboot.demo.collegemanagement.dao.CourseRepository;
import com.springboot.demo.collegemanagement.dao.StudentRepository;
import com.springboot.demo.collegemanagement.entity.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class StudentServiceImplTest {

    private StudentService studentService;
    private StudentRepository studentRepository;



    @Test
    void findAll() {
        studentRepository = mock(StudentRepository.class);
        studentService = new StudentServiceImpl(studentRepository);

        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1, "Kathy","Smith", "person1@mail.com",2020,"CIVIL"));
        studentList.add(new Student(2, "Ron","Weasley", "person2@mail.com",2017,"MECH"));


        when(studentRepository.findAllByOrderByLastNameAsc()).thenReturn(studentList);

        List<Student> expectedList = studentService.findAll();

        assertEquals(2, expectedList.size());
    }

    @Test
    void findById() {
        studentRepository = mock(StudentRepository.class);
        studentService = new StudentServiceImpl(studentRepository);

        when(studentRepository.findById(1022)).thenReturn(Optional.of(new Student(1022, "Rachel", "Jane", "rachel@gmail.com",2018,"CSE")));

        Student student = studentService.findById(1022);

        Assertions.assertThat(student.getFirstName()).isEqualTo("Rachel");
        Assertions.assertThat(student.getLastName()).isEqualTo("Jane");
        Assertions.assertThat(student.getEmail()).isEqualTo("rachel@gmail.com");
        verify(studentRepository,times(1)).findById(1022);
    }

    @Test
    void save() {
        studentRepository = mock(StudentRepository.class);
        studentService = new StudentServiceImpl(studentRepository);

        Student student = new Student(1020,"Haley","Dunphy","haley@gmail.com",2020,"CIVIL");
        studentService.save(student);
        verify(studentRepository).save(student);
    }

    @Test
    void deleteById() {
        studentRepository = mock(StudentRepository.class);
        studentService = new StudentServiceImpl(studentRepository);

        studentService.deleteById(1020);
        verify(studentRepository).deleteById(1020);
    }
}