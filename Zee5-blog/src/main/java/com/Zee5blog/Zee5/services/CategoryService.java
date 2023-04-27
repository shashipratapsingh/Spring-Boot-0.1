package com.Zee5blog.Zee5.services;

import java.util.List;

import com.Zee5blog.Zee5.payloads.CatagoryDto;

public interface CategoryService {

	// create

	CatagoryDto createCategory(CatagoryDto catagoryDto);

	// update

	CatagoryDto updatesCatagory(CatagoryDto catagoryDto, Integer categoryId);

	// delete

	public void deleteCategory(Integer categoryId);
	//public CatagoryDto deleteCategory(Integer categoryId);

	// get

	CatagoryDto getCategoryId(Integer catcategoryId);
	// Get All
	
	List<CatagoryDto> getCategories();

}
