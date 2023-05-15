package com.kisaan.jai.service.impl;

import com.kisaan.jai.dto.VillageDTO;
import com.kisaan.jai.dto.VillageMetaDataDTO;
import com.kisaan.jai.entity.District;
import com.kisaan.jai.entity.Tahsil;
import com.kisaan.jai.entity.Village;
import com.kisaan.jai.repository.VillageRepository;
import com.kisaan.jai.service.VillageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VillageServiceImpl implements VillageService {

    @NotNull
    private final VillageRepository villageRepository;

    @NotNull
    private final ModelMapper modelMapper;

    @NotNull
    private final TahsilServiceImpl tahsilService;

    @NotNull
    private final DistrictServiceImpl districtService;

    protected Village getVillageById(Long villageId) {
        return villageRepository.findByIdAndIsActive(villageId, true)
                .orElseThrow(() -> new NoSuchElementException("Village doesn't exist for id : "+villageId));
    }

    @Override
    public VillageDTO findById(Long villageId) {
        Village village = getVillageById(villageId);

        return getVillageDetails(village);
    }

    @Override
    public List<VillageDTO> findAll() {
        return villageRepository.findAllByIsActive(true)
                .stream()
                .map(this::getVillageDetails)
                .collect(Collectors.toList());
    }

    @Override
    public List<VillageDTO> getVillagesByMetaData(VillageMetaDataDTO villageMetaDataDTO) {
        return villageRepository.findAllByIsActive(true)
                .stream()
                .map(this::getVillageDetails)
                .filter(villageDTO -> villageDTO.getMetaData().equals(villageMetaDataDTO))
                .collect(Collectors.toList());
    }
    private VillageDTO getVillageDetails(Village village) {
        VillageDTO villageDTO = getVillageDTO(village);
        Tahsil tahsil = tahsilService.getTahsilById(village.getTahsil().getId());
        District district = districtService.getDistrictById(tahsil.getDistrict().getId());
        VillageMetaDataDTO villageMetaDataDTO = VillageMetaDataDTO.builder()
                .stateId(district.getState().getId())
                .districtId(district.getId())
                .tahsilId(tahsil.getId()).build();

        villageDTO.setMetaData(villageMetaDataDTO);
        villageDTO.setPlots(null);
        return villageDTO;
    }

    private VillageDTO getVillageDTO(Village village) {
        return modelMapper.map(village, VillageDTO.class);
    }
}
