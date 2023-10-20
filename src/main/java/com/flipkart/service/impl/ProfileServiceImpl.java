package com.flipkart.service.impl;

import com.flipkart.Model.Profile;
import com.flipkart.repository.ProfileRepository;
import com.flipkart.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Override
    public Profile createProfile(Profile profile) {
        String registrationNumber = generateRegistrationNumber();
        profile.setRegistrationNumber(registrationNumber);
        return this.profileRepository.save(profile);
    }

    @Override
    public List<Profile> allProfile() {
        return this.profileRepository.findAll();
    }
    @Override
    public Profile findByRegistrationNumber(String registrationNumber) {
        return this.profileRepository.findByRegistrationNumber(registrationNumber);
    }

/*------------------used for logics only-------------------------------*/
    private String generateRegistrationNumber() {
        return "REG" + System.currentTimeMillis();
    }
}
