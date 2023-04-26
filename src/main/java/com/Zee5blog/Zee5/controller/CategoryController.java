package com.Zee5blog.Zee5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Zee5blog.Zee5.payloads.ApiResponse;
import com.Zee5blog.Zee5.payloads.CatagoryDto;
import com.Zee5blog.Zee5.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	

	// create
	@PostMapping("/create-category")
	public ResponseEntity<CatagoryDto> createCategory(@RequestBody CatagoryDto catagoryDto) {
		CatagoryDto catagoryCreated = this.categoryService.createCategory(catagoryDto);
		return new ResponseEntity<>(catagoryCreated, HttpStatus.CREATED);
	}

	// update

	@PutMapping("/{categoryId}")
	public ResponseEntity<CatagoryDto> updatedCategory(@RequestBody CatagoryDto catagoryDto,
			@PathVariable Integer categoryId) {
		CatagoryDto catagoryUpdated = this.categoryService.updatesCatagory(catagoryDto, categoryId);
		return new ResponseEntity<CatagoryDto>(catagoryUpdated, HttpStatus.CREATED);
	}

	// delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
		this.categoryService.deleteCategory(categoryId);
		// CatagoryDto updateCategory = this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse(), HttpStatus.OK);
	}

	// get

	@GetMapping("/{categoryId}")
	public ResponseEntity<CatagoryDto> categorfindById(@PathVariable Integer categoryId) {
		CatagoryDto catagoryDto = this.categoryService.getCategoryId(categoryId);
		return new ResponseEntity<CatagoryDto>(catagoryDto, HttpStatus.OK);
	}
	// getall
	
	/*
	 * public ResponseEntity<List<CatagoryDto>> categories() {
	 * 
	 * }
	 */

}
