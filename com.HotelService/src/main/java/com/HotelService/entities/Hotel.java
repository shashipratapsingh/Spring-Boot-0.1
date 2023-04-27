package com.HotelService.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "HotelDetails1")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Hotel {

    @Id
    private String id;
    @Column(name = "CUSTOMER_NAME", length = 50, nullable = false, unique = false)
    private String name;
    @Column(name = "CUSTOMER_LOCATION", length = 50, nullable = false, unique = false)
    private String location;
    @Column(name = "CUSTOMER_ABOUT", length = 50, nullable = false, unique = false)
    private String about;
    @Column(name = "CUSTOMER_ADDRESS", length = 50, nullable = false, unique = false)
    private String address;
   /* @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @JsonFormat(pattern = "MM-dd-yyyy")
    private Date dob;*/
    @UpdateTimestamp
    private Date updatedBy;
    @CreationTimestamp
    private Date createdBy;



}
