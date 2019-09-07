package com.demo.springdemo.controllers;

import com.demo.springdemo.bean.ExampleBean;
import com.demo.springdemo.services.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final TestService service;
    private final ExampleBean exampleBean;

    public TestController(TestService service, ExampleBean exampleBean) {
        this.service = service;
        this.exampleBean = exampleBean;
    }

    @GetMapping("/say")
    public String Say(){
       return this.service.say("123");
    }

    @GetMapping("/example/{code}")
    public String example(@PathVariable String code){
        return this.exampleBean.findByCode(code);
    }
}
