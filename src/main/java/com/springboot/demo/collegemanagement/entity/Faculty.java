package com.springboot.demo.collegemanagement.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    @NotNull(message = "This field is required")
    @Size(min=1,message = "is required")
    private String firstName;
    @Column(name = "last_name")
    @NotNull(message = "This field is required")
    @Size(min=1,message = "is required")
    private String lastName;
    @Column(name = "email")
    @NotNull(message = "This field is required")
    @Email(regexp = ".+@.+\\..+", message = "Enter a valid Email Id")
    private String email;
    @Column(name = "qualification")
    @NotNull(message = "This field is required")
    @Size(min=1,message = "is required")
    private String qualification;
    @Column(name = "position")
    @NotNull(message = "This field is required")
    @Size(min=1,message = "is required")
    private String position;
    @Column(name = "dept")
    @NotNull(message = "This field is required")
    @Size(min=1,message = "is required")
    private String dept;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "facultyId", cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Course> courses;

    public Faculty() {
    }

    public Faculty(int id, String firstName, String lastName, String email, String qualification, String position, String dept) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.qualification = qualification;
        this.position = position;
        this.dept = dept;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", qualification='" + qualification + '\'' +
                ", position='" + position + '\'' +
                ", dept='" + dept + '\'' +
                '}';
    }
}
