package com.kisaan.jai.repository;

import com.kisaan.jai.entity.CropExpertMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropExpertRepository extends JpaRepository<CropExpertMapping, Long> {
}
