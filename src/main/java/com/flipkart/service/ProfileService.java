package com.flipkart.service;

import com.flipkart.Model.Profile;

import java.util.List;

public interface ProfileService {

    public Profile createProfile(Profile profile);

    public List<Profile> allProfile();

    Profile findByRegistrationNumber(String registrationNumber);

}
