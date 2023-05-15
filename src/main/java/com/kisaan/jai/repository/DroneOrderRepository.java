package com.kisaan.jai.repository;

import com.kisaan.jai.entity.DroneOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface DroneOrderRepository extends JpaRepository<DroneOrder, Long>{

	List<DroneOrder> findByFarmerId(Long farmerId);
	
	Optional<DroneOrder> findDroneOrderByFarmerIdAndId(Long farmerId, Long id);

	List<DroneOrder> findByFarmerIdAndCreatedDateBetween(Long farmerId, ZonedDateTime startDate, ZonedDateTime endDate);
}