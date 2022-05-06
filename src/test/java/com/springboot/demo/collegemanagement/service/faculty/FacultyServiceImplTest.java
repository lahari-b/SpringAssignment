package com.springboot.demo.collegemanagement.service.faculty;

import com.springboot.demo.collegemanagement.dao.FacultyRepository;
import com.springboot.demo.collegemanagement.entity.Faculty;
import com.springboot.demo.collegemanagement.exception.faculty.FacultyNotFoundException;
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
class FacultyServiceImplTest{

    private FacultyRepository facultyRepository;
    private FacultyServiceImpl facultyService;

    @BeforeEach
    public void setUp() throws Exception {
        facultyRepository= mock(FacultyRepository.class);
        facultyService=new FacultyServiceImpl(facultyRepository);
    }

    @Test
    void findAll() {

        List<Faculty> facultyList = new ArrayList<>();
        facultyList.add(new Faculty(201,"Abhishek","Shetty","abhi@gmail.com","MS CS","Asst. Prof","CSE"));
        facultyList.add(new Faculty(202,"Harshita","Sharma","harshita@yahoo.co.in","BTech MECH","Lab Incharge","Mech"));


        when(facultyRepository.findAllByOrderByLastNameAsc()).thenReturn(facultyList);

        List<Faculty> expectedList = facultyService.findAll();

        assertEquals(2, expectedList.size());
    }

    @Test
    void findById() {

        Faculty faculty=new Faculty(501,"Lucky","Singh","luckt@gmail.com","MTech EE","Prof","ECE");

        when(facultyRepository.findById(501)).thenReturn(Optional.of(faculty));

        Faculty faculty1=facultyService.findById(501);

        Assertions.assertThat(faculty1.getId()).isEqualTo(501);
        Assertions.assertThat(faculty1.getFirstName()).isEqualTo("Lucky");
        Assertions.assertThat(faculty1.getQualification()).isEqualTo("MTech EE");
        verify(facultyRepository,times(1)).findById(501);
    }

    @Test
    void findByIdNotFound(){
        Throwable exception=assertThrows(FacultyNotFoundException.class, () -> {
            Faculty faculty = facultyService.findById(501);
        });
        assertEquals("Did not find Faculty Id - 501", exception.getMessage());
    }

    @Test
    void save() {

        Faculty faculty=new Faculty(501,"Lucky","Singh","luckt@gmail.com","MTech EE","Prof","ECE");

        facultyService.save(faculty);
        verify(facultyRepository).save(faculty);
    }

    @Test
    void deleteById() {

        facultyService.deleteById(501);
        verify(facultyRepository).deleteById(501);
    }
}