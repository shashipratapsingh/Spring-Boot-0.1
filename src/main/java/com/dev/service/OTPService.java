package com.dev.service;

import com.dev.model.OTP;
import com.dev.repository.OTPRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OTPService {

    @Autowired
    private OTPRepository otpRepository;

    public String generateOTP(String mobileNumber) {
        String otp = generateRandomOTP();
        // Store the OTP and set an expiration time in the repository
        OTP otpData = new OTP();
        otpData.setMobileNumber(mobileNumber);
        otpData.setOtp(otp);
        otpData.setExpirationTime(LocalDateTime.now().plusMinutes(5)); // Example: OTP expires in 5 minutes
        otpRepository.save(otpData);
        // Send OTP to the mobile number (implement this logic)
        sendOTPToMobile(mobileNumber, otp);
        return otp;
    }

    public boolean verifyOTP(String mobileNumber, String otp) {
        // Retrieve the OTP data from the repository
        Optional<OTP> otpData = otpRepository.findByMobileNumber(mobileNumber);
        if (otpData.isPresent()) {
            OTP storedOTP = otpData.get();
            // Check if OTP is valid and not expired
            if (otp.equals(storedOTP.getOtp()) && LocalDateTime.now().isBefore(storedOTP.getExpirationTime())) {
                // Delete the used OTP from the repository
                otpRepository.delete(storedOTP);
                return true;
            }
        }
        return false;
    }

    private String generateRandomOTP() {
        // Implement OTP generation logic (e.g., using Apache Commons Text)
        return RandomStringUtils.randomNumeric(6); // Generate a 6-digit OTP
    }

    private void sendOTPToMobile(String mobileNumber, String otp) {
        // Implement OTP sending logic (e.g., using SMS gateway or other communication channels)
    }
}