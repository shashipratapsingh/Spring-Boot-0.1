package com.kisaan.jai.service.impl;

import com.kisaan.jai.dto.TahsilDTO;
import com.kisaan.jai.entity.Tahsil;
import com.kisaan.jai.repository.TahsilRepository;
import com.kisaan.jai.service.TahsilService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TahsilServiceImpl implements TahsilService {

    @NotNull
    private final TahsilRepository tahsilRepository;

    @NotNull
    private final ModelMapper modelMapper;

    protected Tahsil getTahsilById(Long tahsilId) {
        return tahsilRepository.findById(tahsilId)
                .orElseThrow(() -> new NoSuchElementException("Tahsil doesn't exist for id : "+tahsilId));
    }
    @Override
    public TahsilDTO findById(Long tahsilId) {
        Tahsil tahsil = getTahsilById(tahsilId);
        return getTahsilDTO(tahsil);
    }

    @Override
    public List<TahsilDTO> findAll() {
        return tahsilRepository.findAll()
                .stream()
                .map(this::getTahsilDTO)
                .collect(Collectors.toList());
    }

    private TahsilDTO getTahsilDTO(Tahsil tahsil) {
        return modelMapper.map(tahsil, TahsilDTO.class);
    }
}
