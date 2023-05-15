package com.kisaan.jai.service.impl;

import com.kisaan.jai.entity.CropPlotMapping;
import com.kisaan.jai.repository.CropPlotRepository;
import com.kisaan.jai.service.CropPlotMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
public class CropPlotMappingServiceImpl implements CropPlotMappingService {

    @NotNull
    private final CropPlotRepository cropPlotRepository;

    @Override
    public CropPlotMapping save(CropPlotMapping cropPlotMapping) {
        return cropPlotRepository.save(cropPlotMapping);
    }

    @Override
    public CropPlotMapping update(Long aLong, CropPlotMapping cropPlotMapping) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
