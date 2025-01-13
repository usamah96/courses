package com.microservice.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Configuration
public class Config {

    @Autowired private Environment environment;

    @PostConstruct
    public void config(){
        System.out.println(environment.getProperty("spring.security.user.password"));
    }
}
