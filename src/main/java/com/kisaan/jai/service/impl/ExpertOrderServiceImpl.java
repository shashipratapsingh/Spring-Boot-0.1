package com.kisaan.jai.service.impl;

import com.kisaan.jai.dto.ExpertOrderDTO;
import com.kisaan.jai.entity.ExpertOrder;
import com.kisaan.jai.exception.NoSuchElementExistException;
import com.kisaan.jai.repository.ExpertOrderRepository;
import com.kisaan.jai.service.ExpertOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpertOrderServiceImpl implements ExpertOrderService {

	@NotNull
	private final ExpertOrderRepository expertOrderRepository;
	@NotNull
	private final ModelMapper modelMapper;
	@NotNull
	private final FarmerServiceImpl farmerService;
	@NotNull
	private final ZoomServiceImpl zoomService;

	@Override
	public ExpertOrderDTO save(ExpertOrderDTO expertOrderDTO) {
		farmerService.findById(expertOrderDTO.getFarmerId());

		ExpertOrder tempExpertOrder = modelMapper.map(expertOrderDTO, ExpertOrder.class);
		ExpertOrder expertOrder=this.expertOrderRepository.save(tempExpertOrder);
		return getExpertOrderDTO(expertOrder);
	}
	
	
	
	@Override
	public ExpertOrderDTO findById(Long id) {
		ExpertOrder expertOrder = expertOrderRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementExistException("Expert Order doesn't exist for Id :"+ id));

		return getExpertOrderDTO(expertOrder);
	}
	
	
	@Override
	public List<ExpertOrderDTO> findAll() {
		List<ExpertOrder> expertOrders = expertOrderRepository.findAll();
		List<ExpertOrderDTO> expertList = expertOrders.stream()
				.map(this::getExpertOrderDTO)
				.collect(Collectors.toList());
		return expertList;
	}
		
	@Override
	public ExpertOrderDTO update(Long id, ExpertOrderDTO expertOrderDTO) {
		farmerService.findById(expertOrderDTO.getFarmerId());
		zoomService.findById(expertOrderDTO.getExpertId());

		ExpertOrder tempExpertOrder = modelMapper.map(expertOrderDTO, ExpertOrder.class);
		tempExpertOrder.setId(id);

		ExpertOrder expertOrder = expertOrderRepository.save(tempExpertOrder);
		return getExpertOrderDTO(expertOrder);
	}

	@Override
	public void delete(Long id) {
		expertOrderRepository.deleteById(id);
	}

	@Override
	public List<ExpertOrderDTO> getAllByFarmerIdAndExpertId(Long farmerId, Long expertId) {

		List<ExpertOrder> expertOrders = expertOrderRepository.findByFarmerIdAndExpertId(farmerId, expertId);
		log.info("data is expert order "+expertOrders);
		if (expertOrders.isEmpty()){
			throw new NoSuchElementExistException(
					new StringBuilder(" order is not exist this with this farmer id ").append(farmerId).append(" and expert id ").append(expertId).toString());
		}

		List<ExpertOrderDTO> orderDto = expertOrders.stream()
				.map(this::getExpertOrderDTO)
				.collect(Collectors.toList());
		return orderDto;
	}

	@Override
	public ExpertOrderDTO getExpertOrderIdAndFarmerIdAndExpertId(Long id, Long farmerId, Long expertId) {
		ExpertOrder expertOrder = expertOrderRepository.findByIdAndFarmerIdAndExpertId(id, farmerId, expertId)
				.orElseThrow(() -> new NoSuchElementExistException(
						new StringBuilder(" expert order is not exists ").append(id).append(" under this expert id ").append(expertId)
								.append(" under this farmer id ").append(farmerId).toString()));
		return getExpertOrderDTO(expertOrder);
	}

	private ExpertOrderDTO getExpertOrderDTO(ExpertOrder expertOrder) {
		return modelMapper.map(expertOrder, ExpertOrderDTO.class);
	}
}
