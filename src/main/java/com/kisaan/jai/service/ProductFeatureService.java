package com.kisaan.jai.service;

import com.kisaan.jai.dto.ProductFeatureDTO;

import java.util.List;

public interface ProductFeatureService {
		
	ProductFeatureDTO addProductFeature(ProductFeatureDTO productFeatureDto);
	
	List<ProductFeatureDTO> getProductFeatures(Long productId);
	
	ProductFeatureDTO getProductFeature(Long productId, Long featureId);
	
	void deleteProductFeature(Long productId, Long featureId);
	
	ProductFeatureDTO updateProductFeature(Long productId, Long featureId, ProductFeatureDTO productFeatureDto);
}