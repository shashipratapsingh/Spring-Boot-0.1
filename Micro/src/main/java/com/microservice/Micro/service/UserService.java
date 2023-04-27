package com.microservice.Micro.service;

import com.microservice.Micro.entity.Hotel;
import com.microservice.Micro.entity.Rating;
import com.microservice.Micro.entity.User;

import com.microservice.Micro.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;


import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

   @Autowired
     private RestTemplate myRestTemplate;
  private Logger loggerFactory = LoggerFactory.getLogger(UserService.class);


    public User saveDate(User user) {
     String registrationID=  UUID.randomUUID().toString();
     user.setRegistrationID(registrationID);
        return userRepository.save(user);
    }
     // using rest template
    public User getById(@PathVariable String registrationID) {
       User user=userRepository.findByregistrationID(registrationID);

        // fatching rating form above user form user repository

ArrayList<Rating> ratingOfUsers= myRestTemplate.getForObject("http://RATING-SERVICE/rating/getRatingByUserId/"+ user.getRegistrationID(),ArrayList.class);
        loggerFactory.info("{} "  ,ratingOfUsers);


        //user.setRating(ratingOfUsers);
        user.setRating(ratingOfUsers);
        return user;
        //return userRepository.findById(id).get();
    }


    public List<User> getListOfUser() {
        // fatching rating form above user form user repository using grest Template
       List<User> user=userRepository.findAll(); //http://localhost:8081/admin/getListOfUser
       return user;
    }

    public void deleteById(String registrationID) {
        userRepository.deleteById(registrationID);
    }

}
