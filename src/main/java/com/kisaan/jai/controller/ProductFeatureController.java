package com.kisaan.jai.controller;

import com.kisaan.jai.dto.ProductFeatureDTO;
import com.kisaan.jai.payload.response.ApiResponse;
import com.kisaan.jai.payload.response.ResponseHandler;
import com.kisaan.jai.service.ProductFeatureService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/product")
@Slf4j
@AllArgsConstructor
public class ProductFeatureController {
	
	private ProductFeatureService productFeatureService;
	
	@PostMapping("/feature")
	public ResponseEntity<Object> addProduct(@RequestBody ProductFeatureDTO productFeatureDto){
		log.info("inside the controller add  method");
		ProductFeatureDTO addProductFeature = this.productFeatureService.addProductFeature(productFeatureDto);
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(addProductFeature).httpStatus(HttpStatus.CREATED).build());
	}

	/*@GetMapping("/features")
	public ResponseEntity<Object> getproductFeatures(@PathVariable Long productId){
		log.info("inside the controller find all  method");
		List<ProductFeatureDTO> productFeatures = this.productFeatureService.getProductFeatures(productId);
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(productFeatures).count(productFeatures.size()).httpStatus(HttpStatus.OK).build());
	}

	@GetMapping("/feature/{featureId}")
	public ResponseEntity<Object> getProductFeature(@PathVariable Long productId, @PathVariable Long featureId){
		log.info("inside the controller find by id  method");
		ProductFeatureDTO productFeature = this.productFeatureService.getProductFeature(productId, featureId);
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(productFeature).httpStatus(HttpStatus.OK).build());
	}
	
	@DeleteMapping("/feature/{featureId}")
	public ResponseEntity<Object> deleteProductFeature(@PathVariable Long productId, @PathVariable Long featureId){
		log.info("inside the controller delete method");
		this.productFeatureService.deleteProductFeature(productId, featureId);
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().message("Product feature deleted successfully.").httpStatus(HttpStatus.OK).build());
	}
	
	@PutMapping("/feature/{featureId}")
	public ResponseEntity<Object> updateProduct(@PathVariable Long productId, @PathVariable Long featureId, @RequestBody ProductFeatureDTO productFeatureDto){
		log.info("inside the controller update  method");
		ProductFeatureDTO addProductFeature = this.productFeatureService.updateProductFeature(productId, featureId, productFeatureDto);
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(addProductFeature).build());
	}*/
}
