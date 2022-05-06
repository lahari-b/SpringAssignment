package com.springboot.demo.collegemanagement.service.course;

import com.springboot.demo.collegemanagement.dao.CourseRepository;
import com.springboot.demo.collegemanagement.entity.Course;
import com.springboot.demo.collegemanagement.entity.Faculty;
import com.springboot.demo.collegemanagement.exception.course.CourseException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
class CourseServiceImplTest{

    private CourseRepository courseRepository;
    private CourseServiceImpl courseService;

    @BeforeEach
    public void setUp() throws Exception {
        courseRepository=mock(CourseRepository.class);
        courseService=new CourseServiceImpl(courseRepository);
    }

    @Test
    void findAll() {

        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course("DBMS"));
        courseList.add(new Course("HTML"));


        when(courseRepository.findAllByOrderByTitleAsc()).thenReturn(courseList);

        List<Course> expectedList = courseService.findAll();

        assertEquals(2, expectedList.size());
    }

    @Test
    void findByIdNotFound(){
        Throwable exception=assertThrows(CourseException.class, () -> {
            Course course = courseService.findById(20);
        });
        assertEquals("Did not find Course Id - 20", exception.getMessage());
    }
    @Test
    void findById() {

        Faculty faculty=new Faculty(501,"Lucky","Singh","luckt@gmail.com","MTech EE","Prof","ECE");

        when(courseRepository.findById(20)).thenReturn(Optional.of(new Course(20,"JAVA",faculty)));

        Course course= courseService.findById(20);

        Assertions.assertThat(course.getTitle()).isEqualTo("JAVA");
        Assertions.assertThat(course.getFacultyId().getId()).isEqualTo(501);
        Assertions.assertThat(course.getFacultyId().getFirstName()).isEqualTo("Lucky");
        Assertions.assertThat(course.getFacultyId().getQualification()).isEqualTo("MTech EE");
        verify(courseRepository,times(1)).findById(20);
    }

    @Test
    void save() {

        Faculty faculty=new Faculty(501,"Lucky","Singh","luckt@gmail.com","MTech EE","Prof","ECE");
        Course course=new Course(20,"JAVA",faculty);

        when(courseRepository.save(any(Course.class))).thenReturn(course);
        course.setTitle("Python");

        Course updated = courseService.save(course);

        Assertions.assertThat(updated.getTitle()).isEqualTo("Python");
        Assertions.assertThat(updated.getFacultyId().getFirstName()).isEqualTo("Lucky");

        verify(courseRepository,times(1)).save(updated);
    }

    @Test
    void deleteById() {
        courseService.deleteById(20);
        verify(courseRepository).deleteById(20);
    }
}