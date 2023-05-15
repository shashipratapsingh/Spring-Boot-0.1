package com.kisaan.jai.repository;

import com.kisaan.jai.entity.ExpertOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpertOrderRepository extends JpaRepository<ExpertOrder, Long>{

    List<ExpertOrder> findByFarmerIdAndExpertId(Long farmerId, Long expertId);

    Optional<ExpertOrder> findByIdAndFarmerIdAndExpertId(Long expertOrderId, Long farmerId, Long expertId);
}
