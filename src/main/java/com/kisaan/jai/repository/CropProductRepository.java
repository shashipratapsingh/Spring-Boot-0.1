package com.kisaan.jai.repository;

import com.kisaan.jai.entity.CropProductMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropProductRepository extends JpaRepository<CropProductMapping, Long> {
}
