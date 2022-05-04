package com.springboot.demo.collegemanagement.service.faculty;

import com.springboot.demo.collegemanagement.dao.FacultyRepository;
import com.springboot.demo.collegemanagement.entity.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {
    private FacultyRepository facultyRepository;

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public List<Faculty> findAll() {
        return facultyRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Faculty findById(int theId) {
        Optional<Faculty> result=facultyRepository.findById(theId);

        Faculty theFaculty=null;

        if(result.isPresent()){
            theFaculty=result.get();
        }
        else{
            throw new RuntimeException("Did not find Faculty Id - "+theId);
        }

        return theFaculty;
    }

    @Override
    public void save(Faculty theFaculty) {
        facultyRepository.save(theFaculty);
    }

    @Override
    public void deleteById(int theId) {
        facultyRepository.deleteById(theId);
    }
}
