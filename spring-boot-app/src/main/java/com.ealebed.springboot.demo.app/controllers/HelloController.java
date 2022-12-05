package com.ealebed.springboot.demo.app.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    @RequestMapping("/")
    public String index() {
        return "Greetings to SoftServians from Spring Boot and Jib!";
    }
}
