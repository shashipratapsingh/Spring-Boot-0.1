package com.kisaan.jai.controller;

import com.kisaan.jai.dto.CategoryIdDTO;
import com.kisaan.jai.dto.ProductIdDTO;
import com.kisaan.jai.dto.SubCategoryDTO;
import com.kisaan.jai.dto.SubCategoryIdDTO;
import com.kisaan.jai.payload.response.ApiResponse;
import com.kisaan.jai.payload.response.ResponseHandler;
import com.kisaan.jai.service.SubCategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/catalogue/subcategory")
@Slf4j
public class SubCategoryController {

    private SubCategoryService subCategoryService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllSubcategory(@Valid @RequestBody CategoryIdDTO categoryIdDTO){
        log.info("inside the add sub category method");
        List<SubCategoryDTO> subcategories = subCategoryService.getSubcategoriesByCategoryId(categoryIdDTO.getCategoryId());
        return ResponseHandler.prepareResponse(
                ApiResponse.builder().responseBody(subcategories).count(subcategories.size()).httpStatus(HttpStatus.OK).build());
    }

    @GetMapping
    public ResponseEntity<Object> getSubCategory(@Valid @RequestBody SubCategoryIdDTO subCategoryIdDTO){
        log.info("inside the get sub category method");
        SubCategoryDTO getSubCategory = subCategoryService.getSubcategoriesByCategoryId(subCategoryIdDTO.getCategoryId(), subCategoryIdDTO.getSubCategoryId());
        return ResponseHandler.prepareResponse(
                ApiResponse.builder().responseBody(getSubCategory).count(1).httpStatus(HttpStatus.OK).build());
    }
}
