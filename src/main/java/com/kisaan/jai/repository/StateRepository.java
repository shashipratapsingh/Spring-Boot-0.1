package com.kisaan.jai.repository;

import com.kisaan.jai.entity.ProductCategory;
import com.kisaan.jai.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    Optional<State> findByIdAndIsActive(Long statId, boolean isActive);

    List<ProductCategory> findAllByIsActive(boolean isActive);
}
