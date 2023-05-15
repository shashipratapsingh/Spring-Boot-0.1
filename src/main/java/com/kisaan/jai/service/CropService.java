package com.kisaan.jai.service;

import com.kisaan.jai.common.service.BaseGetService;
import com.kisaan.jai.common.service.BaseService;
import com.kisaan.jai.common.service.SearchService;
import com.kisaan.jai.dto.CropDTO;

public interface CropService extends BaseService<CropDTO, Long>,
        BaseGetService<CropDTO, Long>,
        SearchService<CropDTO, Long> {

    CropDTO findByIdAndProductCategoryDetails(Long cropId);

    CropDTO findByIdAndProductDetails(Long cropId, Long categoryId);

    CropDTO findByIdAndProductDetails(Long cropId, Long categoryId, Long subCategoryId);
}
