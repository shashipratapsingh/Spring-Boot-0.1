package com.kisaan.jai.repository;

import com.kisaan.jai.entity.ProductCategory;
import com.kisaan.jai.entity.Village;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VillageRepository extends JpaRepository<Village, Long> {
    Optional<Village> findByIdAndIsActive(Long villageId, boolean isActive);

    List<Village> findAllByIsActive(boolean isActive);
}
