package com.kisaan.jai.service.impl;

import com.kisaan.jai.dto.OrderSoilDTO;
import com.kisaan.jai.entity.OrderSoil;
import com.kisaan.jai.exception.NoSuchElementExistException;
import com.kisaan.jai.repository.OrderSoilRepository;
import com.kisaan.jai.service.OrderSoilService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class OrderSoiServiceImpl implements OrderSoilService {

	private OrderSoilRepository orderSoilRepository;
	private FarmerServiceImpl farmerService;

	private ModelMapper modelMapper;

	@Override
	public OrderSoilDTO save(OrderSoilDTO orderSoilDTO) {
		farmerService.findById(orderSoilDTO.getFarmerId());
		OrderSoil tempOrderSoil = this.getOrderSoil(orderSoilDTO);
		OrderSoil orderSoil = this.orderSoilRepository.save(tempOrderSoil);
		return this.getOrderSoil(orderSoil);
	}

	@Override
	public OrderSoilDTO update(Long id, OrderSoilDTO orderSoilDTO) {
		farmerService.findById(orderSoilDTO.getFarmerId());
		getByFarmerIdAndId(orderSoilDTO.getFarmerId(), id);
		OrderSoil tempOrderSoil = this.getOrderSoil(orderSoilDTO);
		tempOrderSoil.setId(id);
		OrderSoil orderSoil = this.orderSoilRepository.save(tempOrderSoil);
		return this.getOrderSoil(orderSoil);
	}
	
	@Override
	public List<OrderSoilDTO> getAllByFarmerId(Long id) {
		List<OrderSoil> ordersByFarmer = orderSoilRepository.findByFarmerIdAndIsActive(id, true);
		List<OrderSoilDTO> orders = ordersByFarmer.stream()
				.map(this::getOrderSoil).collect(Collectors.toList());
		if (orders.isEmpty()){
			throw new NoSuchElementExistException("With this farmer id does not exists any orders");
		}
		return orders;
	}

	@Override
	public List<OrderSoilDTO> getAllByFarmerIdAndDateRange(Long id, ZonedDateTime startDate, ZonedDateTime endDate) {
		List<OrderSoil> ordersByFarmer = orderSoilRepository.findByFarmerIdAndIsActiveAndCreatedDateBetween(id, true, startDate, endDate);
		List<OrderSoilDTO> orders = ordersByFarmer.stream()
				.map(this::getOrderSoil).collect(Collectors.toList());
		if (orders.isEmpty()){
			throw new NoSuchElementExistException("order is not exists with farmer id along with this date range ");
		}
		return orders;
	}

	@Override
	public OrderSoilDTO getByFarmerIdAndId(Long farmerId, Long id) {
		OrderSoil orderSoil = orderSoilRepository.findOrderSoilByFarmerIdAndIdAndIsActive(farmerId, id, true)
				.orElseThrow(() -> new NoSuchElementExistException(
						new StringBuilder(" order is not exist this id ").append(id).append(" with this farmer id ").append(farmerId).toString()));
		return this.getOrderSoil(orderSoil);
	}



	@Override
	public void delete(Long id) {
		OrderSoil orderSoil = orderSoilRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementExistException(
						new StringBuilder("Soil order doesn't exist for id :").append(id).toString()));
		orderSoil.setIsActive(false);

		orderSoilRepository.save(orderSoil);
	}

	private OrderSoil getOrderSoil(OrderSoilDTO orderSoilDTO) {
		return modelMapper.map(orderSoilDTO, OrderSoil.class);
	}

	private OrderSoilDTO getOrderSoil(OrderSoil orderSoil) {
		return modelMapper.map(orderSoil, OrderSoilDTO.class);
	}
}