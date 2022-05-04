package com.springboot.demo.collegemanagement.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@PropertySource("application.properties")
public class DemoAppConfig {

    @Autowired
    private Environment env;

    private Logger logger=Logger.getLogger(getClass().getName());


    @Bean
    public DataSource securityDataSource(){

        ComboPooledDataSource securityDataSource=new ComboPooledDataSource();

        try {
            securityDataSource.setDriverClass(env.getProperty("spring.datasource.jdbc.driver"));
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }

        logger.info(">>> jdbc.url="+env.getProperty("spring.datasource.url"));
        logger.info(">>> jdbc.user="+env.getProperty("spring.datasource.username"));

        securityDataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
        securityDataSource.setUser(env.getProperty("spring.datasource.username"));
        securityDataSource.setPassword(env.getProperty("spring.datasource.password"));

        securityDataSource.setInitialPoolSize(getIntProperty("spring.datasource.connection.pool.initialPoolSize"));

        securityDataSource.setMinPoolSize(getIntProperty("spring.datasource.connection.pool.minPoolSize"));
        securityDataSource.setMaxPoolSize(getIntProperty("spring.datasource.connection.pool.maxPoolSize"));
        securityDataSource.setMaxIdleTime(getIntProperty("spring.datasource.connection.pool.maxIdleTime"));
        
        return securityDataSource;
    }

    private int getIntProperty(String propName){
        String propVal=env.getProperty(propName);
        int intPropVal=Integer.parseInt(propVal);
        return intPropVal;
    }
}
