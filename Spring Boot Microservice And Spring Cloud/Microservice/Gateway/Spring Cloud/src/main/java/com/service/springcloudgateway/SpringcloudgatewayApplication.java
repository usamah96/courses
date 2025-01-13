package com.service.springcloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringcloudgatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudgatewayApplication.class, args);
    }

}
