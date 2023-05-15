package com.kisaan.jai.service.impl;

import com.kisaan.jai.dto.StateDTO;
import com.kisaan.jai.entity.State;
import com.kisaan.jai.repository.StateRepository;
import com.kisaan.jai.service.StateService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StateServiceImpl implements StateService {

    @NotNull
    private final StateRepository stateRepository;

    @NotNull
    private final ModelMapper modelMapper;

    protected State getStateById(Long stateId) {
        return stateRepository.findById(stateId).orElseThrow(() -> new NoSuchElementException(
                new StringBuilder("State doesn't exist for id : ").append(stateId).toString())
        );
    }

    @Override
    public StateDTO findById(Long stateId) {
        State state = getStateById(stateId);
        return getStateDTO(state);
    }

    @Override
    public List<StateDTO> findAll() {
        return stateRepository.findAll()
                .stream()
                .map(this::getStateDTO)
                .collect(Collectors.toList());
    }

    private StateDTO getStateDTO(State state) {
        return modelMapper.map(state, StateDTO.class);
    }
}
