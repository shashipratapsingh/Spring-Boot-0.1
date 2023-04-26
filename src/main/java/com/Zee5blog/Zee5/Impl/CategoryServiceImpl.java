package com.Zee5blog.Zee5.Impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Zee5blog.Zee5.exception.ResourceNotFountException;
import com.Zee5blog.Zee5.models.Category;
import com.Zee5blog.Zee5.payloads.CatagoryDto;
import com.Zee5blog.Zee5.repostory.CatagoryRepository;
import com.Zee5blog.Zee5.services.CategoryService;

import io.swagger.v3.oas.annotations.servers.Server;
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CatagoryRepository catagoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CatagoryDto createCategory(CatagoryDto catagoryDto) {
		Category cat = this.modelMapper.map(catagoryDto, Category.class);
		Category cretaeCategory = this.catagoryRepository.save(cat);
		return modelMapper.map(cretaeCategory, CatagoryDto.class);
	}

	@Override
	public CatagoryDto updatesCatagory(CatagoryDto catagoryDto, Integer categoryId) {
		Category cat = this.catagoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFountException("category", "category Id", categoryId));
		cat.setCategoryTitle(catagoryDto.getCategoryTitle());
		cat.setCategoryDescription(catagoryDto.getCategoryDescription());
		Category updatedCat = this.catagoryRepository.save(cat);

		return this.modelMapper.map(updatedCat, CatagoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {

		Category cat = this.catagoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFountException("category", "category Id", categoryId));

		this.catagoryRepository.deleteById(categoryId);
	}

	@Override
	public CatagoryDto getCategoryId(Integer categoryId) {
		Category deletedCat = this.catagoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFountException("category", "category Id", categoryId));
		return this.modelMapper.map(deletedCat, CatagoryDto.class);
	}

	@Override
	public List<CatagoryDto> getCategories() {
		List<Category> categories = this.catagoryRepository.findAll();
		List<CatagoryDto> categoriesDto = categories.stream().map((cat) -> this.modelMapper.map(cat, CatagoryDto.class))
				.collect(Collectors.toList());
		return categoriesDto;
		// .orElseThrow(() -> new ResourceNotFountException("category", "category Id",
		// categoryId));
		// return (List<CatagoryDto>) this.modelMapper.map(getCategories,
		// CatagoryDto.class);
	}

}
