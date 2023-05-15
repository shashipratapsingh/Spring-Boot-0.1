package com.kisaan.jai.repository;

import com.kisaan.jai.entity.ProductSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubCategoryRepository extends JpaRepository<ProductSubCategory, Long>{
    Optional<ProductSubCategory> findByProductSubCategoryIdAndIsActive(Long productSubCategoryId, boolean isActive);

    List<ProductSubCategory> findAllByIsActive(boolean isActive);

    List<ProductSubCategory> findByIsActiveAndProductCategory_ProductCategoryId(Boolean isActive, Long categoryId);

    Optional<ProductSubCategory> findByIsActiveAndProductCategory_ProductCategoryIdAndProductSubCategoryId(Boolean isActive, Long categoryId, Long subCategoryId);

}