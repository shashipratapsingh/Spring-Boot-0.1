package com.microservice.Micro.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Rating {
    private  String ratingId;
    private  String UserId;
    private  String hotelId;
    private  int rating;
    private  String feedback;

    private Hotel hotel;
}
