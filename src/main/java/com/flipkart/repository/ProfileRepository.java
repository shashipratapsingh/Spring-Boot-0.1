package com.flipkart.repository;

import com.flipkart.Model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Long> {
    Profile findByRegistrationNumber(String registrationNumber);
}
