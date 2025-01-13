package com.service.accountmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "account/")
@Slf4j
public class AccountController {

    @GetMapping("list/all")
    public String listAll(){
        log.info("Hello From Account Controller list/all");
        return "Accounts";
    }
}
