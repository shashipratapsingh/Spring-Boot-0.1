package com.security1.controller;

import com.security1.dto.UserDto;
import com.security1.model.Authority;
import com.security1.model.User;
import com.security1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class HomeController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<User> getString(@RequestBody UserDto userDto)
    {
        User user=new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAuthority(userDto.getAuthority());
        return new ResponseEntity<>(this.userRepository.save(user), HttpStatus.CREATED);
    }
    @GetMapping("/dashboard")
    public String getDashboard()
    {
        return "user dashboard page";
    }


    @GetMapping("/profile")
    public String getProfile()
    {
        return "user profile page";
    }
}
