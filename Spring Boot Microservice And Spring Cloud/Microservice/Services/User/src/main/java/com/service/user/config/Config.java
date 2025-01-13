package com.service.user.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Configuration
@Slf4j
public class Config {

    @Autowired private Environment environment;

    @PostConstruct
    public void config(){
        log.info("Test Property: " + environment.getProperty("test.user.service"));
    }
}
