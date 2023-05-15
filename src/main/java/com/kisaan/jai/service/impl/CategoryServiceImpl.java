package com.kisaan.jai.service.impl;

import com.kisaan.jai.dto.CategoryDTO;
import com.kisaan.jai.entity.ProductCategory;
import com.kisaan.jai.exception.NoSuchElementExistException;
import com.kisaan.jai.repository.CategoryRepository;
import com.kisaan.jai.service.CategoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;
	private ModelMapper modelMapper;

	@Override
	public CategoryDTO findById(Long categoryId) {
		ProductCategory tempProductCategory = categoryRepository.findByproductCategoryIdAndIsActive(categoryId, true)
				.orElseThrow(() -> new NoSuchElementExistException(
						new StringBuilder("Category not found for id: ").append(categoryId).toString()));
		return modelMapper.map(tempProductCategory, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> findAll() {
		List<ProductCategory> productCategoryList = categoryRepository.findAllByIsActive(true);
		return productCategoryList.stream()
				.map(c -> modelMapper.map(c, CategoryDTO.class))
				.collect(Collectors.toList());
	}
}
