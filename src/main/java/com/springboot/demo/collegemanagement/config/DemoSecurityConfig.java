package com.springboot.demo.collegemanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource securityDataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(securityDataSource);
    }
    private static final String faculty = "FACULTY";
    private static final String admin = "ADMIN";
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        

        http.authorizeRequests()
                .antMatchers("/").hasAnyRole(admin,"STUDENT",faculty)
                .antMatchers("/adminStaff/**").hasRole(admin)
                .antMatchers("/faculty/showFormForAdd").hasRole(admin)
                .antMatchers("/faculty/showFormForUpdate").hasRole(admin)
                .antMatchers("/faculty/delete").hasRole(admin)
                .antMatchers("/students/list").hasAnyRole(admin,faculty)
                .antMatchers("/students/showFormForAdd").hasRole(admin)
                .antMatchers("/students/showFormForUpdate").hasRole(admin)
                .antMatchers("/students/delete").hasRole(admin)
                .antMatchers("/courses/showFormForAdd").hasAnyRole(admin,faculty)
                .antMatchers("/courses/showFormForUpdate").hasAnyRole(admin,faculty)
                .antMatchers("/courses/delete").hasAnyRole(admin,faculty)
                .and()
                .formLogin()
                .loginPage("/showMyLoginPage")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");
    }
}
