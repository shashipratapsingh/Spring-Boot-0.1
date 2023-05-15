package com.kisaan.jai.repository;

import com.kisaan.jai.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FarmerRepository extends JpaRepository<Farmer, Long>{

	Optional<Farmer> findByIdAndIsActive(Long farmerId, boolean isActive);

	List<Farmer> findAllByIsActive(boolean isActive);

	List<Farmer> findByNameContaining(String name);

	Optional<Farmer> findByMobileAndIsActive(String mobile, boolean isActive);
}
