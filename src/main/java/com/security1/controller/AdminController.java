package com.security1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/home")
    public String getString()
    {
        return "admin Home";
    }
    @GetMapping("/dashboard")
    public String getDashboard()
    {
        return "admin dashboard ";
    }
}
