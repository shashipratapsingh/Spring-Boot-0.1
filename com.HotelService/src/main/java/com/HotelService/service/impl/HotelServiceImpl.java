package com.HotelService.service.impl;

import com.HotelService.entities.Hotel;
import com.HotelService.repository.HotelRepository;
import com.HotelService.service.HotelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelServices {
    @Autowired
    private HotelRepository hotelRepository;


    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public List<Hotel> getHotel() {
        return hotelRepository.findAll();
    }

    public Hotel getByHotel(@PathVariable String id) {
        return hotelRepository.findById(id).get();
    }

    public void deleteBYId(String id) {
        hotelRepository.deleteById(id);
    }

    public Hotel updateHotel(@PathVariable String id) {
        Hotel hotel = hotelRepository.findById(id).get();
        hotel.setAbout(hotel.getAbout());
        hotel.setName(hotel.getName());
        Hotel hotel1 = hotelRepository.save(hotel);
        return hotel1;

    }

    public List<Hotel> getHotelLastRegistered() {
        return hotelRepository.findAll();
    }
}
