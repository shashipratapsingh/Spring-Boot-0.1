package com.kisaan.jai.repository;

import com.kisaan.jai.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long>{

	Optional<Crop> findByCropIdAndIsActive(Long cropId, Boolean isActive);

	List<Crop> findAllByIsActive(Boolean isActive);

	List<Crop> findByNameContainingAndIsActive(String name, Boolean isActive);
}
