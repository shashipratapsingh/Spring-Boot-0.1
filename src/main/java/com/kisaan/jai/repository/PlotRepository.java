package com.kisaan.jai.repository;

import com.kisaan.jai.entity.Plot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlotRepository extends JpaRepository<Plot, Long> {

    Optional<Plot> findByPlotIdAndIsActive(Long plotId, boolean isActive);

    List<Plot> findAllByIsActive(boolean isActive);

    List<Plot> findByIsActiveAndFarmer_Id(boolean isActive, Long farmerId);
}
