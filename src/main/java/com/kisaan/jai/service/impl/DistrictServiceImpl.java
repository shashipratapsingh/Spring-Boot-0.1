package com.kisaan.jai.service.impl;

import com.kisaan.jai.dto.DistrictDTO;
import com.kisaan.jai.entity.District;
import com.kisaan.jai.repository.DistrictRepository;
import com.kisaan.jai.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    @NotNull
    private final DistrictRepository districtRepository;

    @NotNull
    private final ModelMapper modelMapper;

    protected District getDistrictById(Long districtId) {
        return districtRepository.findByIdAndIsActive(districtId, true)
                .orElseThrow(() -> new NoSuchElementException("District doesn't exist for id : "+districtId));
    }
    @Override
    public DistrictDTO findById(Long tahsilId) {
        District district = getDistrictById(tahsilId);
        return getDistrictDTO(district);
    }

    @Override
    public List<DistrictDTO> findAll() {
        return districtRepository.findAllByIsActive(true)
                .stream()
                .map(this::getDistrictDTO)
                .collect(Collectors.toList());
    }

    private DistrictDTO getDistrictDTO(District district) {
        return modelMapper.map(district, DistrictDTO.class);
    }
}
