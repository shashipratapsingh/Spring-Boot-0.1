package com.kisaan.jai.service.impl;

import com.kisaan.jai.dto.CropDTO;
import com.kisaan.jai.dto.ZoomConsultantDTO;
import com.kisaan.jai.entity.CropExpertMapping;
import com.kisaan.jai.entity.ZoomConsultant;
import com.kisaan.jai.exception.NoSuchElementExistException;
import com.kisaan.jai.repository.ZoomConsultantRepo;
import com.kisaan.jai.service.CropExpertMappingService;
import com.kisaan.jai.service.ZoomService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ZoomServiceImpl implements ZoomService {

	@NotNull
	private final ZoomConsultantRepo zoomConsultantRepo;

	@NotNull
	private final ModelMapper modelMapper;

	@NotNull
	private final CropExpertMappingService cropExpertMappingService;

	@NotNull
	private final CropServiceImpl cropService;
	
	@Override
	public ZoomConsultantDTO save(ZoomConsultantDTO zoomConsultantDTO) {
		ZoomConsultant tempZoomConsultant = this.modelMapper.map(zoomConsultantDTO, ZoomConsultant.class);
		tempZoomConsultant.setIsActive(true);
		ZoomConsultant zoomConsultant=this.zoomConsultantRepo.save(tempZoomConsultant);

		List<CropExpertMapping> cropExpertMappings = updateCropExpertMapping(tempZoomConsultant, zoomConsultantDTO.getCrops());
		tempZoomConsultant.setCropExpertMappings(cropExpertMappings);
		return getZoomConsultantDetail(zoomConsultant);
	}

	@Override
	public ZoomConsultantDTO findById(Long id) {
		ZoomConsultant zoomConsultant = zoomConsultantRepo.findById(id)
				.orElseThrow(() -> new NoSuchElementExistException("Zoom Consultant doesn't exist for id : " + id));
		return getZoomConsultantDetail(zoomConsultant);
	}

	@Override
	public List<ZoomConsultantDTO> findAll() {
		List<ZoomConsultant> consultants = zoomConsultantRepo.findAll();
		List<ZoomConsultantDTO> zoomConsultantList = consultants.stream()
				.map(this::getZoomConsultantDetail)
				.collect(Collectors.toList());
		return zoomConsultantList;
	}

	@Override
	public ZoomConsultantDTO update(Long id, ZoomConsultantDTO t) {
		ZoomConsultantDTO zoomConsultant = findById(id);
		ZoomConsultant tempZoomConsultant = this.modelMapper.map(t, ZoomConsultant.class);
		tempZoomConsultant.setId(id);
		tempZoomConsultant = this.zoomConsultantRepo.save(tempZoomConsultant);

		List<CropExpertMapping> cropExpertMappings = updateCropExpertMapping(tempZoomConsultant, t.getCrops());
		tempZoomConsultant.setCropExpertMappings(cropExpertMappings);
		return getZoomConsultantDetail(tempZoomConsultant);
	}

	@Override
	public void delete(Long id) {
	}

	private List<CropExpertMapping> updateCropExpertMapping(ZoomConsultant zoomConsultant, List<CropDTO> crops) {
		return crops.stream()
				.map(CropDTO::getId)
				.map(cropService::getCropById)
				.map(crop -> CropExpertMapping.builder().crop(crop).expert(zoomConsultant).build())
				.map(cropExpertMappingService::save).collect(Collectors.toList());
	}

	private ZoomConsultantDTO getZoomConsultantDetail(ZoomConsultant zoomConsultant) {
		ZoomConsultantDTO zoomConsultantDTO = modelMapper.map(zoomConsultant, ZoomConsultantDTO.class);
		if(CollectionUtils.isEmpty(zoomConsultant.getCropExpertMappings())) {
			return zoomConsultantDTO;
		}

		List<CropDTO> cropList = zoomConsultant.getCropExpertMappings().stream()
				.map(cem -> cropService.findByIdWithoutProductDetails(cem.getCrop().getCropId(), false))
				.collect(Collectors.toList());

		zoomConsultantDTO.setCrops(cropList);

		return zoomConsultantDTO;
	}
}