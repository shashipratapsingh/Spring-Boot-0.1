package com.microservice.Micro.controller;

import com.microservice.Micro.entity.User;
import com.microservice.Micro.repository.UserRepository;
import com.microservice.Micro.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/saveData")
    public User saveDate(@RequestBody User user)
    {
        return userService.saveDate(user);
    }
 // countRetry=1;
    @GetMapping("/getData/{registrationID}")
    @CircuitBreaker(name = "UserRatingBreaker",fallbackMethod = "ratingFallBack")
    //@Retry(name = "UserRatingBreaker",fallbackMethod = "ratingFallBack"))
    //@RateLimiter(name="userRateLimiter",fallbackMethode="ratingFallBack")
    public User getById(@PathVariable String registrationID)
    {
           // System.out.println(countRetry)
          //countRetry++;
        return userService.getById(registrationID);
    }
    //creating fall back method
    public User ratingFallBack(String registrationID){
      return userService.getById(registrationID);
    }

    @GetMapping("/getListOfUser")
    public List<User> getListOfUser() {
           return userService.getListOfUser();
    }

    @DeleteMapping("/deleteByID/{registrationID}")
    public void deleteByID(@PathVariable String registrationID) {
        userService.deleteById(registrationID);
    }











    /*@PutMapping("/updateById")
    public void updateUser(@PathVariable User user) {
     return userService.updateUser(user.getId());

    }*/


    /*@PutMapping("/updateById/{id}")
    public void updateUser(@PathVariable("id") User user) {
        User userFromDb = userRepository.findById(user.getId()).get();
        // crush the variables of the object found
        userFromDb.setName(user.getName());
        userFromDb.setEmail(user.getEmail());
        userFromDb.setAbout(user.getAbout());
        userRepository.save(userFromDb);
    }*/
}
