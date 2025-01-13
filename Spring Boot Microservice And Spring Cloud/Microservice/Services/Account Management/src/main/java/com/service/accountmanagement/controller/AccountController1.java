package com.service.accountmanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "accounts/")
public class AccountController1 {

    @PostMapping("list/alls")
    public ResponseEntity<List<String>> listAll(@RequestBody List<String> values){
        return ResponseEntity.status(HttpStatus.OK).body(values);
    }
}
