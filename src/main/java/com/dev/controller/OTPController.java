package com.dev.controller;

import com.dev.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/otp")
public class OTPController {

    @Autowired
    private OTPService otpService;

    @PostMapping("/send")
    public ResponseEntity<String> sendOTP(@RequestParam String mobileNumber) {
        // Generate and send OTP to the mobile number
        String otp = otpService.generateOTP(mobileNumber);
        return ResponseEntity.ok("OTP sent successfully.");
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOTP(@RequestParam String mobileNumber, @RequestParam String otp) {
        if (otpService.verifyOTP(mobileNumber, otp)) {
            return ResponseEntity.ok("OTP verification successful.");
        } else {
            return ResponseEntity.badRequest().body("Invalid OTP.");
        }
    }
}