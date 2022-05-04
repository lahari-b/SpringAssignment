package com.springboot.demo.collegemanagement.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
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
    @Column(name = "joining_year")
    @NotNull(message = "This field is required")
    private int joiningYear;
    @Column(name = "dept")
    @NotNull(message = "This field is required")
    @Size(min=1,message = "is required")
    private String dept;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "course_student",
            joinColumns=@JoinColumn(name = "student_id"),
            inverseJoinColumns=@JoinColumn(name = "course_id")
    )
    private List<Course> courses;

    public Student() {
    }

    public Student(int id, String firstName, String lastName, String email, int joiningYear, String dept) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.joiningYear = joiningYear;
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

    public int getJoiningYear() {
        return joiningYear;
    }

    public void setJoiningYear(int joiningYear) {
        this.joiningYear = joiningYear;
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
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", joiningYear=" + joiningYear +
                ", dept='" + dept + '\'' +
                '}';
    }
}
