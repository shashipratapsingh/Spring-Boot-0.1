package com.kisaan.jai.repository;

import com.kisaan.jai.entity.District;
import com.kisaan.jai.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    Optional<District> findByIdAndIsActive(Long districtId, boolean isActive);

    List<District> findAllByIsActive(boolean isActive);
}
