package com.kisaan.jai.repository;

import com.kisaan.jai.entity.ProductFeature;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductFeatureRepository extends JpaRepository<ProductFeature, Long>{
	
	List<ProductFeature> findAllProductFeatureByProduct_ProductId(Long productId);
	
	Optional<ProductFeature> findProductFeatureByProduct_ProductIdAndId(Long productId, Long id);

}
