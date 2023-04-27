package com.microservice.Micro.repository;

import com.microservice.Micro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByregistrationID(String registrationID);

    void deleteById(String registrationID);
}
