package com.service.user.controller;

import com.service.user.dto.UserDto;
import com.service.user.entities.UserEntity;
import com.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "user/")
public class UserController {

    private Environment environment;
    private UserService userService;

    @Autowired
    public UserController(Environment environment, UserService userService) {
        this.environment = environment;
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userEntity));
    }

    @GetMapping("/test")
    public ResponseEntity<String> userTest() {
        System.out.println(environment.getProperty("spring.cloud.bus.test"));
        return ResponseEntity.status(HttpStatus.OK).body("Testing User On Port: " + environment.getProperty("local.server.port"));
    }

    @PostMapping("/test/post")
    public ResponseEntity<String> userTestPost(@RequestBody List<Long> ids) {
        return ResponseEntity.status(HttpStatus.CREATED).body("Testing User On Port: " + environment.getProperty("local.server.port"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> userById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(id));
    }

    @GetMapping("/feign/{id}")
    public ResponseEntity<UserDto> userByIdUsingFeignClient(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByIdUsingFeignClient(id));
    }
}

