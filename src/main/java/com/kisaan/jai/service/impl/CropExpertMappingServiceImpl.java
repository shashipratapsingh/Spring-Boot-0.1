package com.kisaan.jai.service.impl;

import com.kisaan.jai.entity.CropExpertMapping;
import com.kisaan.jai.entity.CropPlotMapping;
import com.kisaan.jai.repository.CropExpertRepository;
import com.kisaan.jai.repository.CropPlotRepository;
import com.kisaan.jai.service.CropExpertMappingService;
import com.kisaan.jai.service.CropPlotMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
public class CropExpertMappingServiceImpl implements CropExpertMappingService {

    @NotNull
    private final CropExpertRepository cropExpertRepository;

    @Override
    public CropExpertMapping save(CropExpertMapping cropExpertMapping) {
        return cropExpertRepository.save(cropExpertMapping);
    }

    @Override
    public CropExpertMapping update(Long aLong, CropExpertMapping cropExpertMapping) {
        cropExpertMapping.setId(aLong);
        return cropExpertRepository.save(cropExpertMapping);
    }

    @Override
    public void delete(Long aLong) {

    }
}
