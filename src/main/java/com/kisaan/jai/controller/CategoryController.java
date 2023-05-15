package com.kisaan.jai.controller;

import com.kisaan.jai.dto.CategoryDTO;
import com.kisaan.jai.dto.CategoryIdDTO;
import com.kisaan.jai.payload.response.ApiResponse;
import com.kisaan.jai.payload.response.ResponseHandler;
import com.kisaan.jai.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1")
@Slf4j
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/catalogue/category/all")
	public ResponseEntity<Object> getAllCategory() {
		log.info("Invoked CategoryController getAllCategory method.");
		List<CategoryDTO> categoryList = categoryService.findAll();
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(categoryList).httpStatus(HttpStatus.OK).message("Data found").count(categoryList.size()).build());
	}

	@GetMapping("/catalogue/category")
	public ResponseEntity<Object> getCategory(@Valid @RequestBody CategoryIdDTO categoryIdDTO) {
		log.debug("Invoked CategoryController getCategory method, categoryId : {}", categoryIdDTO.getCategoryId());
		CategoryDTO categoryDTO = categoryService.findById(categoryIdDTO.getCategoryId());
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(categoryDTO).message("Data found").httpStatus(HttpStatus.OK).build());
	}
}
