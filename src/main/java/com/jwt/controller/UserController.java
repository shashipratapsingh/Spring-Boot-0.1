package com.jwt.controller;

import com.jwt.Dao.UserDoa;
import com.jwt.entity.User;
import com.jwt.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("{registerNewUser}")
    public User resistrerNewUser(@RequestBody User user)
    {
        return this.userService.resisterNewUser(user);
    }


    @PostConstruct
    public void initRolesAdminAndUser()
    {
        userService.initRolesAdminAndUser();
    }

    @GetMapping("/{forAdmin}")
    public String forAdmin()
    {
        return "for admin accessible";
    }

    @GetMapping("/{forUser}")
    public String forUser()
    {
        return "for user accessible";
    }

}
