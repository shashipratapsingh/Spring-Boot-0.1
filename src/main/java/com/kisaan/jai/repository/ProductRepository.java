package com.kisaan.jai.repository;

import com.kisaan.jai.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	Optional<Product> findByProductIdAndIsActive(Long productId, boolean isActive);

	List<Product> findAllByIsActive(boolean isActive);

	List<Product> findByIsActiveAndProductSubCategory_ProductSubCategoryIdAndProductSubCategory_ProductCategory_ProductCategoryId(Boolean isActive, Long subCategoryId, Long categoryId);

	Optional<Product> findByProductIdAndIsActiveAndProductSubCategory_ProductSubCategoryIdAndProductSubCategory_ProductCategory_ProductCategoryId(Long productId, Boolean isActive, Long subCategoryId, Long categoryId);

	List<Product> findByNameContainingAndIsActive(String name, Boolean isActive);

	List<Product> findByNameAndIsActiveAndProductSubCategory_ProductSubCategoryIdAndProductSubCategory_ProductCategory_ProductCategoryId(String name, Boolean isActive, Long subCategoryId, Long categoryId);

}
