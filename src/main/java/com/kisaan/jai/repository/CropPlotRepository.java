package com.kisaan.jai.repository;

import com.kisaan.jai.entity.CropPlotMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropPlotRepository extends JpaRepository<CropPlotMapping, Long> {
}
