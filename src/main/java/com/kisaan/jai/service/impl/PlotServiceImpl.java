package com.kisaan.jai.service.impl;

import com.kisaan.jai.dto.CropDTO;
import com.kisaan.jai.dto.FarmerDTO;
import com.kisaan.jai.dto.PlotDTO;
import com.kisaan.jai.dto.VillageDTO;
import com.kisaan.jai.entity.*;
import com.kisaan.jai.exception.ElementAlreadyExistException;
import com.kisaan.jai.exception.NoSuchElementExistException;
import com.kisaan.jai.repository.PlotRepository;
import com.kisaan.jai.service.CropPlotMappingService;
import com.kisaan.jai.service.PlotService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlotServiceImpl implements PlotService {

    @NotNull
    private final PlotRepository plotRepository;
    @NotNull
    private final ModelMapper modelMapper;

    @NotNull
    private final VillageServiceImpl villageService;

    @NotNull
    private final CropPlotMappingService cropPlotMappingService;

    private CropServiceImpl cropService;

    public void setCropService(CropServiceImpl cropService) {
        this.cropService = cropService;
    }

    @Override
    public PlotDTO findById(Long plotId) {
        Plot plot = findPlotById(plotId);
        return getPlot(plot);
    }

    @Override
    public List<PlotDTO> findAll() {
        List<Plot> plots = plotRepository.findAllByIsActive(true);
        List<PlotDTO> plotList = plots.stream()
                .map(this::getPlot)
                .collect(Collectors.toList());
        return plotList;
    }

    @Override
    public PlotDTO save(PlotDTO plotDTO) {
        Plot tempPlot = modelMapper.map(plotDTO, Plot.class);

        if(!ObjectUtils.isEmpty(plotDTO.getFarmer())) {
            Farmer farmer = modelMapper.map(plotDTO.getFarmer(), Farmer.class);
            tempPlot.setFarmer(farmer);
        }

        if(!ObjectUtils.isEmpty(plotDTO.getVillage())) {
            Village village = villageService.getVillageById(plotDTO.getVillage().getId());
            tempPlot.setVillage(village);
        }

        Plot plot = plotRepository.save(tempPlot);

        if(!CollectionUtils.isEmpty(plotDTO.getCrops())) {
            List<Plot> cropList = plotDTO.getCrops().stream().
                    map(c -> {
                        Crop crop = modelMapper.map(c, Crop.class);
                        crop.setCropId(c.getId());
                        return crop;
                    })
                    .map(crop -> addCropPlotMapping(crop, plot, modelMapper.map(plotDTO.getFarmer(), Farmer.class)))
                    .collect(Collectors.toList());
        }

        return this.findById(plot.getPlotId());
    }

    @Override
    public PlotDTO update(Long plotId, PlotDTO plotDTO) {
        PlotDTO tempPlotDTO = this.findById(plotId);
        if(ObjectUtils.isEmpty(tempPlotDTO)) {
            throw new ElementAlreadyExistException("Plot doesn't exist.");
        }
        Plot tempPlot = modelMapper.map(plotDTO, Plot.class);
        tempPlot.setPlotId(plotId);

        FarmerDTO farmerDTO = plotDTO.getFarmer();
        if(ObjectUtils.isEmpty(farmerDTO)) {
            farmerDTO = tempPlotDTO.getFarmer();
        }

        Farmer farmer = modelMapper.map(farmerDTO, Farmer.class);
        tempPlot.setFarmer(farmer);

        if(!ObjectUtils.isEmpty(plotDTO.getVillage())) {
            Village village = villageService.getVillageById(plotDTO.getVillage().getId());
            tempPlot.setVillage(village);
        }

        Plot plot = plotRepository.save(tempPlot);

        /*if(!CollectionUtils.isEmpty(plotDTO.getCrops())) {
            List<Plot> cropList = plotDTO.getCrops().stream().
                    map(c -> {
                        Crop crop = modelMapper.map(c, Crop.class);
                        crop.setCropId(c.getId());
                        return crop;
                    })
                    .map(crop -> addCropPlotMapping(crop, plot))
                    .collect(Collectors.toList());
        }*/
        plotDTO.setId(plot.getPlotId());
        return plotDTO;
    }

    @Override
    public void delete(Long plotId) {
        Plot plot = findPlotById(plotId);
        plot.setIsActive(Boolean.FALSE);
        plotRepository.save(plot);
    }

    private Plot findPlotById(Long plotId) {
        return plotRepository.findByPlotIdAndIsActive(plotId, true)
                .orElseThrow(() -> new NoSuchElementExistException(
                        new StringBuilder("Plot not found for id: ").append(plotId).toString()));
    }

    private PlotDTO getPlot(Plot plot) {
        PlotDTO plotDTO = modelMapper.map(plot, PlotDTO.class);

        VillageDTO village = villageService.findById(plotDTO.getVillage().getId());
        plotDTO.setVillage(village);

        if(CollectionUtils.isEmpty(plot.getCropList())) {
            return plotDTO;
        }
        List<CropDTO> cropList = plot.getCropList().stream()
                .map(cpm -> cropService.findByIdWithoutPlotDetails(cpm.getCrop().getCropId(), false))
                .collect(Collectors.toList());

        plotDTO.setCrops(cropList);

        return plotDTO;
    }

    private Plot addCropPlotMapping(Crop crop, Plot plot, Farmer farmer) {
        CropPlotMapping cropPlotMapping = new CropPlotMapping();
        cropPlotMapping.setCrop(crop);
        cropPlotMapping.setPlot(plot);
        cropPlotMapping.setFarmer(farmer);

        cropPlotMappingService.save(cropPlotMapping);
        return plot;
    }

    @Override
    public List<PlotDTO> getPlotsByFarmerId(Long farmerId) {
        return plotRepository.findByIsActiveAndFarmer_Id(true, farmerId).stream()
                .map(this::getPlot).collect(Collectors.toList());
    }
}
