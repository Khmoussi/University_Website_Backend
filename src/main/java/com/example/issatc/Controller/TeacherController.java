package com.example.issatc.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/teacher")
public class TeacherController {
    @GetMapping("/hello")
    String hello(){
        return "hello teacher";
    }
}
