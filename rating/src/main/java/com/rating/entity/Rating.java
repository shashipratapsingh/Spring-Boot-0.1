package com.rating.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name="Rating_Details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    @Id
    private String ratingId;
    private String  userId;
    private  String hotelId;
    private int rating;
    private String feedback;

}
