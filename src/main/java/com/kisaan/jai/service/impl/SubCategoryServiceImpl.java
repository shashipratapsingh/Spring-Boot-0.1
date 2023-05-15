package com.kisaan.jai.service.impl;

import com.kisaan.jai.dto.SubCategoryDTO;
import com.kisaan.jai.entity.ProductSubCategory;
import com.kisaan.jai.exception.NoSuchElementExistException;
import com.kisaan.jai.repository.CategoryRepository;
import com.kisaan.jai.repository.SubCategoryRepository;
import com.kisaan.jai.service.SubCategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SubCategoryServiceImpl implements SubCategoryService {

    private SubCategoryRepository subCategoryRepository;
    private ModelMapper modelMapper;
    private CategoryRepository categoryRepository;

    protected ProductSubCategory getSubCategory(Long subCategoryId) {
        ProductSubCategory productSubCategory = subCategoryRepository.findByProductSubCategoryIdAndIsActive(subCategoryId, true)
                .orElseThrow(() -> new NoSuchElementExistException(
                        new StringBuilder("Subcategory not found for id: ").append(subCategoryId).toString()));

        return productSubCategory;
    }

    @Override
    public SubCategoryDTO getSubcategoriesByCategoryId(Long categoryId, Long subCategoryId) {
        ProductSubCategory productSubCategory = getProductSubCategory(categoryId, subCategoryId);
        return this.modelMapper.map(productSubCategory, SubCategoryDTO.class);
    }

    protected ProductSubCategory getProductSubCategory(Long categoryId, Long subCategoryId) {
        return subCategoryRepository.findByIsActiveAndProductCategory_ProductCategoryIdAndProductSubCategoryId(true, categoryId, subCategoryId)
                .orElseThrow(() -> new NoSuchElementExistException("Sub category not found under this category id " + categoryId));
    }


    @Override
    public List<SubCategoryDTO> getSubcategoriesByCategoryId(Long categoryId) {
        List<ProductSubCategory> productSubCategoryList = subCategoryRepository.findByIsActiveAndProductCategory_ProductCategoryId(true, categoryId);
        if (productSubCategoryList.isEmpty()) {
			throw new NoSuchElementExistException(" no sub categories found out for this category id : "+categoryId);
		}
        return productSubCategoryList.stream()
                .map(sc -> modelMapper.map(sc, SubCategoryDTO.class))
                .collect(Collectors.toList());
    }

}
