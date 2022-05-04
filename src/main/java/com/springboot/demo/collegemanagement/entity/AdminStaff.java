package com.springboot.demo.collegemanagement.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "adminstaff")
public class AdminStaff {

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

    public AdminStaff(){}

    public AdminStaff(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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

    @Override
    public String toString() {
        return "AdminStaff{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
