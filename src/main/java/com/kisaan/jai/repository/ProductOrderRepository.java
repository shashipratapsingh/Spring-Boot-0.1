package com.kisaan.jai.repository;

import com.kisaan.jai.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long>{

	List<ProductOrder> findByFarmerId(Long farmerId);
	
	Optional<ProductOrder> findProductOrderByFarmerIdAndId(Long farmerId, Long id);

	List<ProductOrder> findByFarmerIdAndCreatedDateBetween(Long farmerId, ZonedDateTime startDate, ZonedDateTime endDate);
}
