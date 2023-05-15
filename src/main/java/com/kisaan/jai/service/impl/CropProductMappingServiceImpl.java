package com.kisaan.jai.service.impl;

import com.kisaan.jai.entity.CropProductMapping;
import com.kisaan.jai.repository.CropProductRepository;
import com.kisaan.jai.service.CropProductMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
public class CropProductMappingServiceImpl implements CropProductMappingService {

    @NotNull
    private final CropProductRepository cropProductRepository;

    @Override
    public CropProductMapping save(CropProductMapping cropProductMapping) {
        return cropProductRepository.save(cropProductMapping);
    }

    @Override
    public CropProductMapping update(Long aLong, CropProductMapping cropProductMapping) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
