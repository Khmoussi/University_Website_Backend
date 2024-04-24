package com.example.issatc.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/student")
public class StudentController {
    @GetMapping("/hello")
    String hello(){
        return "hello student";
    }
}
