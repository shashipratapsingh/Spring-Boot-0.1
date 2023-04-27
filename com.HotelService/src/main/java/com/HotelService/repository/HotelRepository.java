package com.HotelService.repository;

import com.HotelService.entities.Hotel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,String> {

    //List<Product> findAllByPrice(double price, Pageable pageable);
}
