package com.HotelService.service;

import com.HotelService.entities.Hotel;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface HotelServices {

    Hotel saveHotel(Hotel hotel);

    List<Hotel> getHotel();

    Hotel getByHotel(@PathVariable String id);

    void deleteBYId(String id);

    Hotel updateHotel(@PathVariable String id);

    List<Hotel> getHotelLastRegistered();
}
