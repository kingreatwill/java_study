package com.demo.springdemo.controllers;

import com.demo.springdemo.services.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final TestService service;

    public TestController(TestService service) {
        this.service = service;
    }

    @GetMapping("/say")
    public String Say(){
       return this.service.say("123");
    }
}
