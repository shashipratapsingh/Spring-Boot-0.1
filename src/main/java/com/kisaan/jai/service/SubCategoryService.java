package com.kisaan.jai.service;

import com.kisaan.jai.common.service.BaseGetService;
import com.kisaan.jai.dto.SubCategoryDTO;

import java.util.List;

public interface SubCategoryService{

    List<SubCategoryDTO> getSubcategoriesByCategoryId(Long categoryId);
    
    SubCategoryDTO getSubcategoriesByCategoryId(Long categoryId, Long subCategoryId);
    
}
