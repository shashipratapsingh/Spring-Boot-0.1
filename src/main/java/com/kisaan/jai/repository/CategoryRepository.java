package com.kisaan.jai.repository;

import com.kisaan.jai.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<ProductCategory, Long>{

    Optional<ProductCategory> findByproductCategoryIdAndIsActive(Long categoryId, boolean isActive);

    List<ProductCategory> findAllByIsActive(boolean isActive);
}
