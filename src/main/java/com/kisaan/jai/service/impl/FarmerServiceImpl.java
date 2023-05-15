package com.kisaan.jai.service.impl;

import com.kisaan.jai.dto.*;
import com.kisaan.jai.entity.Farmer;
import com.kisaan.jai.exception.ElementAlreadyExistException;
import com.kisaan.jai.exception.NoSuchElementExistException;
import com.kisaan.jai.repository.FarmerRepository;
import com.kisaan.jai.service.FarmerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FarmerServiceImpl implements FarmerService {
	@NotNull
	private final FarmerRepository farmerRepository;
	@NotNull
	private final ModelMapper modelMapper;

	@NotNull
	private final PlotServiceImpl plotService;

	@NotNull
	private final CropServiceImpl cropService;

	@Override
	@Transactional
	public FarmerDTO save(FarmerDTO farmerDTO)throws ElementAlreadyExistException {
		log.info("inside the save method in service layer");

		Optional<Farmer> optionalFarmer = farmerRepository.findByMobileAndIsActive(farmerDTO.getMobile(), true);
		if(optionalFarmer.isPresent()) {
			throw new ElementAlreadyExistException("Mobile number already in use. Try with different number.");
		}
		Farmer tempFarmer = modelMapper.map(farmerDTO, Farmer.class);
		tempFarmer.setPlots(null);
		Farmer farmer = farmerRepository.save(tempFarmer);

		farmerDTO.setId(farmer.getId());
		PlotDTO plotDTO = savePlot(farmerDTO);
		updatePlotDetails(farmerDTO);

		return this.findById(farmer.getId());
	}

	private PlotDTO savePlot(FarmerDTO farmerDTO) {
		VillageDTO villageDTO = new VillageDTO();
		villageDTO.setId(farmerDTO.getVillageId());
		VillageMetaDataDTO villageMetaDataDTO = VillageMetaDataDTO.builder()
				.stateId(farmerDTO.getStateId())
				.districtId(farmerDTO.getDistrictId())
				.tahsilId(farmerDTO.getTahsilId()).build();

		villageDTO.setMetaData(villageMetaDataDTO);
		PlotDTO plotDTO = new PlotDTO();
		plotDTO.setId(farmerDTO.getPlotId());
		plotDTO.setPlotInfo(farmerDTO.getPlotInfo());
		plotDTO.setVillage(villageDTO);
		plotDTO.setCrops(farmerDTO.getCrops());
		plotDTO.setFarmer(farmerDTO);

		farmerDTO.setPlots(Arrays.asList(plotDTO));
		return plotService.save(plotDTO);
	}

	@Override
	public FarmerDTO findById(Long id) {
		log.info("inside the find by id method in service layer");
		Farmer farmer = farmerRepository.findByIdAndIsActive(id, true)
				.orElseThrow(() -> new NoSuchElementExistException("Farmer doesn't exist for id : "+ id));
		log.info("farmer data is "+farmer);
		return getFarmerDetails(farmer);
	}

	@Override
	public List<FarmerDTO> findAll() {
		log.info("inside the list of farmer method in service layer");
		List<Farmer> listFarmer = farmerRepository.findAllByIsActive(true);

		if(listFarmer.isEmpty()) {
			throw new NoSuchElementExistException("farmers are not exists ");
		}
		List<FarmerDTO> farmerDTOList = listFarmer.stream().map(this::getFarmerDetails)
				.collect(Collectors.toList());

		return farmerDTOList;
	}

	@Override
	@Transactional
	public FarmerDTO update(Long id, FarmerDTO farmerDTO) {
		log.info("inside the update farmer method in service layer");
		Farmer tempFarmer = this.modelMapper.map(farmerDTO, Farmer.class);
		farmerRepository.findByIdAndIsActive(id, true)
				.orElseThrow(() -> new NoSuchElementExistException("Farmer doesn't exist for id : "+ id));
		tempFarmer.setId(id);
		tempFarmer.setPlots(null);
		Farmer farmer = this.farmerRepository.save(tempFarmer);

		savePlot(farmerDTO);
		updatePlotDetails(farmerDTO);

		return this.findById(farmer.getId());
	}

	private List<PlotDTO> updatePlotDetails(FarmerDTO farmerDTO) {
		List<PlotDTO> plotList = farmerDTO.getPlots().stream().map(p -> getPlotDetails(p))
				.map(p -> {
					p.setFarmer(farmerDTO);
					return p;
				})
				.map(p -> plotService.update(p.getId(), p)).collect(Collectors.toList());

		return plotList;
	}

	private PlotDTO getPlotDetails(PlotDTO plot) {
		List<CropDTO> cropDTOList = plot.getCrops().stream()
				.map(cropDTO -> cropService.findByIdWithoutProductDetails(cropDTO.getId(), false))
				.collect(Collectors.toList());
		PlotDTO plotDTO = plotService.findById(plot.getId());
		plotDTO.setCrops(cropDTOList);
		return plotDTO;
	}

	@Override
	public void delete(Long id) {
		log.info("inside the delete of farmer method in service layer");
		Farmer farmer = this.farmerRepository.findByIdAndIsActive(id, true)
				.orElseThrow(() -> new NoSuchElementExistException("Farmer doesn't exist for id : "+ id));
		farmer.setIsActive(Boolean.FALSE);
		farmerRepository.save(farmer);
	}

	private FarmerDTO getFarmerDetails(Farmer farmer) {
		FarmerDTO farmerDTO = modelMapper.map(farmer, FarmerDTO.class);
		farmerDTO.setPlots(null);
		List<PlotDTO> plots = plotService.getPlotsByFarmerId(farmer.getId());

		if(CollectionUtils.isEmpty(plots)) {
			return farmerDTO;
		}

		List<PlotDTO> plotList = plots.stream()
				.map(p -> {
					farmerDTO.setVillageId(p.getVillage().getId());
					farmerDTO.setStateId(p.getVillage().getMetaData().getStateId());
					farmerDTO.setDistrictId(p.getVillage().getMetaData().getDistrictId());
					farmerDTO.setTahsilId(p.getVillage().getMetaData().getTahsilId());
					if(!CollectionUtils.isEmpty(p.getCrops())) {
						List<CropDTO> cropDTOList = p.getCrops().stream()
								.map(c -> {
									c.setProducts(null);
									return c;
								}).collect(Collectors.toList());
						p.setCrops(cropDTOList);
						p.setVillage(null);
					}

					return p;
				}).collect(Collectors.toList());

		farmerDTO.setPlots(plotList);
		return farmerDTO;
	}







}