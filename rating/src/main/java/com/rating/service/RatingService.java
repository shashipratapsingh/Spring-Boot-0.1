package com.rating.service;

import com.rating.repository.RatingRepository;
import com.rating.entity.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class RatingService {


    @Autowired
    private RatingRepository ratingRepository;

    public RatingService() {
    }

    public Rating saveDate(Rating rating) {
        String RatingId=  UUID.randomUUID().toString();
        rating.setRatingId(RatingId);
        return  ratingRepository.save(rating);
    }

    public Rating getById(@PathVariable int ratingId) {

        return ratingRepository.findById(ratingId).get();
    }
    public List<Rating> getListOfUser() {
        return ratingRepository.findAll();
    }


    // special methode which I have created....



    public List<Rating> findByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

 //   public List<Rating> findByUserId1(String userId)

    public List<Rating> findByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }






    public void deleteById(int id) {
        ratingRepository.deleteById(id);
    }
}
