package com.kisaan.jai.service.impl;

import com.kisaan.jai.dto.ProductFeatureDTO;
import com.kisaan.jai.entity.ProductFeature;
import com.kisaan.jai.exception.NoSuchElementExistException;
import com.kisaan.jai.repository.ProductFeatureRepository;
import com.kisaan.jai.service.ProductFeatureService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProductFeatureServiceImpl implements ProductFeatureService{

	private ProductFeatureRepository productFeatureRepository;

	private ModelMapper modelMapper;
	
	@Override
	public ProductFeatureDTO addProductFeature(ProductFeatureDTO productFeatureDto) {
		log.info("inside the service add method");
		ProductFeature productFeature = this.modelMapper.map(productFeatureDto, ProductFeature.class);
		ProductFeature save = this.productFeatureRepository.save(productFeature);
		return this.modelMapper.map(save, ProductFeatureDTO.class);
	}

	@Override
	public List<ProductFeatureDTO> getProductFeatures(Long productId) {
		log.info("inside the service find all method");
		//productService.findById(productId);
		List<ProductFeature> listProductFeatures = this.productFeatureRepository.findAllProductFeatureByProduct_ProductId(productId);
		if(listProductFeatures.isEmpty()) {
			throw new NoSuchElementExistException("Product feature is not exist under this products ");
		}
		List<ProductFeatureDTO> collect = listProductFeatures.stream()
				.map((productFeature) -> this.modelMapper.map(productFeature, ProductFeatureDTO.class))
				.collect(Collectors.toList());
		return collect;
	}

	@Override
	public ProductFeatureDTO getProductFeature(Long productId, Long featureId) {
		log.info("inside the service find by id method");
		//productService.findById(productId);
		Optional<ProductFeature> feature = this.productFeatureRepository.findProductFeatureByProduct_ProductIdAndId(productId, featureId);
		if(feature.get()==null) {
			throw new NoSuchElementExistException(" feature is not exists under this productss");
		}
		ProductFeatureDTO productFeatureDto = this.modelMapper.map(feature, ProductFeatureDTO.class);
		return productFeatureDto;
	}

	@Override
	public void deleteProductFeature(Long productId, Long featureId) {
		log.info("inside the service delete method");
		//productService.findById(productId);
		ProductFeature feature = productFeatureRepository.findProductFeatureByProduct_ProductIdAndId(productId, featureId)
				.orElseThrow(() -> new NoSuchElementExistException("no exists with this "));
		this.productFeatureRepository.delete(feature);
	}

	@Override
	public ProductFeatureDTO updateProductFeature(Long productId, Long featureId, ProductFeatureDTO productFeatureDto) {
		log.info("inside the service update method");
		//productService.findById(productId);
		ProductFeature feature=this.productFeatureRepository.findProductFeatureByProduct_ProductIdAndId(productId, featureId)
				.orElseThrow(() -> new NoSuchElementExistException("no exists with this "));
		ProductFeature productFeature = this.modelMapper.map(productFeatureDto, ProductFeature.class);
		feature.setDescription(productFeature.getDescription());
		feature.setId(featureId);
		//feature.setProductId(productId);
		ProductFeature save = this.productFeatureRepository.save(feature);
		return this.modelMapper.map(save, ProductFeatureDTO.class);
	}
}