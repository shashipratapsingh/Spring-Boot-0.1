package com.kisaan.jai.controller;

import com.kisaan.jai.dto.CropDTO;
import com.kisaan.jai.dto.CropIdDTO;
import com.kisaan.jai.payload.response.ApiResponse;
import com.kisaan.jai.payload.response.ResponseHandler;
import com.kisaan.jai.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/crop")
public class CropController {

	@Autowired
	private CropService cropService;

	@PostMapping
	public ResponseEntity<Object> addCrop(@Valid @RequestBody CropDTO cropDTO) {
		CropDTO saveCropDTO = cropService.save(cropDTO);
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().status(true).message("Saved Successfully").httpStatus(HttpStatus.CREATED).build());
	}

	@GetMapping("/all")
	public ResponseEntity<Object> getAllCrop() {
		List<CropDTO> cropList = cropService.findAll();
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(cropList).status(true).httpStatus(HttpStatus.OK).message("Success").count(cropList.size()).build());
	}

	@GetMapping("/search")
	public ResponseEntity<Object> searchByName(@RequestBody CropIdDTO cropIdDTO) {
		List<CropDTO> cropList = cropService.search(cropIdDTO.getName());
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(cropList).status(true).message("Fetched Successfully").httpStatus(HttpStatus.OK).count(cropList.size()).build());
	}
	
	@GetMapping
	public ResponseEntity<Object> getCrop(@Valid @RequestBody CropIdDTO cropIdDTO) {
		CropDTO cropDTO = cropService.findById(cropIdDTO.getCropId());
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(cropDTO).message("Data found").status(true).httpStatus(HttpStatus.OK).build());
	}

	@GetMapping("/category")
	public ResponseEntity<Object> getCropAndCategoryDetails(@Valid @RequestBody CropIdDTO cropIdDTO) {
		CropDTO cropDTO = cropService.findByIdAndProductCategoryDetails(cropIdDTO.getCropId());
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(cropDTO).message("Data found").httpStatus(HttpStatus.OK).build());
	}

	@GetMapping("/category/subcategory")
	public ResponseEntity<Object> getCropAndSubCategoryDetails(@Valid @RequestBody CropIdDTO cropIdDTO) {
		CropDTO cropDTO = cropService.findByIdAndProductDetails(cropIdDTO.getCropId(), cropIdDTO.getCategoryId());
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(cropDTO).status(true).message("Data found").httpStatus(HttpStatus.OK).build());
	}

	@GetMapping("/category/subcategory/product")
	public ResponseEntity<Object> getCropAndProductDetail(@Valid @RequestBody CropIdDTO cropIdDTO) {
		CropDTO cropDTO = cropService.findByIdAndProductDetails(cropIdDTO.getCropId(), cropIdDTO.getCategoryId(), cropIdDTO.getSubCategoryId());
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(cropDTO).message("Data found").status(true).httpStatus(HttpStatus.OK).build());
	}

	@PutMapping
	public ResponseEntity<Object> updateCategory( @Valid @RequestBody CropDTO cropDTO) {
		CropDTO updateCropDTO = cropService.update(cropDTO.getId(), cropDTO);
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().status(true).message("Updated Successfully").httpStatus(HttpStatus.OK).build());
	}

	@DeleteMapping
	public ResponseEntity<Object> deleteCategory(@Valid @RequestBody CropIdDTO cropIdDTO) {
		this.cropService.delete(cropIdDTO.getCropId());
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().message("Crop deleted successfully").status(true).httpStatus(HttpStatus.OK).build());
	}

}