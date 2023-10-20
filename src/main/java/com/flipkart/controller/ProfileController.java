package com.flipkart.controller;

import com.flipkart.CustomException.ErrorResponse;
import com.flipkart.Model.Profile;
import com.flipkart.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class ProfileController {

    @Autowired
    private ProfileService profileService;
    @PostMapping("/")
    public ResponseEntity<Profile> createUserProfile(@RequestBody @Valid Profile profile) {
        return new ResponseEntity<>(this.profileService.createProfile(profile),HttpStatus.CREATED);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Profile>> allProfile() {
        return new ResponseEntity<>(this.profileService.allProfile(), HttpStatus.OK);
    }

    @GetMapping("/findByRegistration/{registrationNumber}")
    public ResponseEntity<?> findByRegistrationNumber(@PathVariable String registrationNumber) {
        Profile profile = this.profileService.findByRegistrationNumber(registrationNumber);

        if (profile != null) {
            return new ResponseEntity<>(profile, HttpStatus.OK);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("Data not found. Please enter the correct Registration number.");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }







}
