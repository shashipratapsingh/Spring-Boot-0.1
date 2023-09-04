package com.dev.model;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Entity
@Table(name="login")
public class OTP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mobileNumber;
    private String otp;
    private LocalDateTime expirationTime;

    public OTP(Long id, String mobileNumber, String otp, LocalDateTime expirationTime) {
        this.id = id;
        this.mobileNumber = mobileNumber;
        this.otp = otp;
        this.expirationTime = expirationTime;
    }

    public OTP() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    @Override
    public String toString() {
        return "OTP{" +
                "id=" + id +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", otp='" + otp + '\'' +
                ", expirationTime=" + expirationTime +
                '}';
    }
}