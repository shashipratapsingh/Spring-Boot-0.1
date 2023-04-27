package com.HotelService.controller;

import com.HotelService.ExceptionHandler.FeedNotFound;
import com.HotelService.Handler.ApiResponse;
import com.HotelService.entities.Hotel;
import com.HotelService.repository.HotelRepository;
import com.HotelService.service.HotelServices;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelServices hotelServices;
    @Autowired
    private HotelRepository hotelRepository;


    @PostMapping(value = "/addHotelDetails")
    public ResponseEntity saveHotel1(@RequestBody Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        Hotel hotel1 = hotelServices.saveHotel(hotel);
        return ResponseEntity.ok(new ApiResponse(Boolean.TRUE, hotel1));
    }

    @GetMapping(value = "/findHotelDetails")
    public List<Hotel> getHotel() {
        return hotelServices.getHotel();
    }


    /*@GetMapping(value = "/findHotelDetails")
    public ResponseEntity<ApiResponse> getHotel1() {
        Hotel hotelIdDetails = (Hotel) hotelServices.getHotel();
        return ResponseEntity.ok(new ApiResponse(Boolean.TRUE,hotelIdDetails));
    }*/


    @GetMapping("/findByID/{id}")
    public ResponseEntity<ApiResponse> getByHotel1(@PathVariable String id) {
        Hotel hotelIdDetails = hotelServices.getByHotel(id);

        if(hotelIdDetails==null){
            throw new FeedNotFound("Hotel ID not found");
        }
        return ResponseEntity.ok(new ApiResponse(Boolean.TRUE,hotelIdDetails));
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable String id) {
        hotelServices.deleteBYId(id);
    }


    //updates
    @PutMapping("/updates/{id}")
    public Hotel updateHotel(@PathVariable String id) {
        return hotelServices.updateHotel(id);

    }

}