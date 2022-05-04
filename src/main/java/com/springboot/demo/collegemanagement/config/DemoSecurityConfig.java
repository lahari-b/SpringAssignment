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

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("ADMIN","STUDENT","FACULTY")
                .antMatchers("/adminStaff/**").hasRole("ADMIN")
                .antMatchers("/faculty/showFormForAdd").hasRole("ADMIN")
                .antMatchers("/faculty/showFormForUpdate").hasRole("ADMIN")
                .antMatchers("/faculty/delete").hasRole("ADMIN")
                .antMatchers("/students/list").hasAnyRole("ADMIN","FACULTY")
                .antMatchers("/students/showFormForAdd").hasRole("ADMIN")
                .antMatchers("/students/showFormForUpdate").hasRole("ADMIN")
                .antMatchers("/students/delete").hasRole("ADMIN")
                .antMatchers("/courses/showFormForAdd").hasAnyRole("ADMIN","FACULTY")
                .antMatchers("/courses/showFormForUpdate").hasAnyRole("ADMIN","FACULTY")
                .antMatchers("/courses/delete").hasAnyRole("ADMIN","FACULTY")
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
