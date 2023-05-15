package com.kisaan.jai.service;

import com.kisaan.jai.common.service.BaseGetService;
import com.kisaan.jai.common.service.BaseService;
import com.kisaan.jai.common.service.SearchService;
import com.kisaan.jai.dto.ProductDTO;
import com.kisaan.jai.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService extends BaseService<ProductDTO, Long>,
        BaseGetService<ProductDTO, Long>,
        SearchService<ProductDTO, Long> {

	
    List<ProductDTO> getProductsByCategoryIdAndSubCategoryId(Long categoryId, Long subCategoryId);

    ProductDTO getProductByCategoryIdAndSubCategoryIdAndProductId(Long categoryId, Long subCategoryId, Long productId);
   
  
}
