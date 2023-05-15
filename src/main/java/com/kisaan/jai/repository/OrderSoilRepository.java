package com.kisaan.jai.repository;

import com.kisaan.jai.entity.OrderSoil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderSoilRepository extends JpaRepository<OrderSoil, Long>{

	List<OrderSoil> findByFarmerIdAndIsActive(Long farmerId, Boolean isActive);
	
	Optional<OrderSoil> findOrderSoilByFarmerIdAndIdAndIsActive(Long farmerId, Long id, Boolean isActive);

	List<OrderSoil> findByFarmerIdAndIsActiveAndCreatedDateBetween(Long farmerId, Boolean isActive, ZonedDateTime startDate, ZonedDateTime endDate);
}