package com.kisaan.jai.service.impl;

import com.kisaan.jai.dto.DroneOrderDTO;
import com.kisaan.jai.entity.DroneOrder;
import com.kisaan.jai.exception.NoSuchElementExistException;
import com.kisaan.jai.repository.DroneOrderRepository;
import com.kisaan.jai.service.DroneOrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class DroneOrderServiceImpl implements DroneOrderService {

	@NotNull
	private final DroneOrderRepository droneOrderRepository;
	private ModelMapper modelMapper;
	private FarmerServiceImpl  farmerService;

	
	@Override
	public DroneOrderDTO save(DroneOrderDTO droneOrderDTO) {
		farmerService.findById(droneOrderDTO.getFarmerId());

		DroneOrder tempDroneOrder = getDroneOrder(droneOrderDTO);
		DroneOrder droneOrder = this.droneOrderRepository.save(tempDroneOrder);
		return getDroneOrderDTO(droneOrder);
	}

	
	@Override
	public DroneOrderDTO findById(Long id) {
		DroneOrder droneOrder = this.droneOrderRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementExistException(
						new StringBuilder("dron order is not exist with this order id : ").append(id).toString()));
		return getDroneOrderDTO(droneOrder);
	}
	
	@Override
	public DroneOrderDTO update(Long id, DroneOrderDTO droneOrderDTO) {
		farmerService.findById(droneOrderDTO.getFarmerId());
		findById(id);
		DroneOrder tempDroneOrder = getDroneOrder(droneOrderDTO);
		tempDroneOrder.setId(id);
		DroneOrder droneOrder = this.droneOrderRepository.save(tempDroneOrder);
		return getDroneOrderDTO(droneOrder);
	}
	
	@Override
	public List<DroneOrderDTO> getAllByFarmerId(Long farmerId) {
		List<DroneOrder> droneOrders = droneOrderRepository.findByFarmerId(farmerId);
		List<DroneOrderDTO> droneOrderList = droneOrders.stream()
				.map(this::getDroneOrderDTO)
				.collect(Collectors.toList());
		if (droneOrderList.isEmpty()){
			throw new NoSuchElementExistException("Dron order is not exist along with this farmer ID "+farmerId);
		}
		return droneOrderList;
	}

	@Override
	public List<DroneOrderDTO> getAllByFarmerIdAndDateRange(Long farmerId, ZonedDateTime startDate, ZonedDateTime endDate) {
		List<DroneOrder> droneOrders = droneOrderRepository.findByFarmerIdAndCreatedDateBetween(farmerId, startDate, endDate);
		List<DroneOrderDTO> droneOrderList = droneOrders.stream()
				.map(this::getDroneOrderDTO)
				.collect(Collectors.toList());
		if (droneOrderList.isEmpty()){
			throw new NoSuchElementExistException("Dron order is not exist along with this farmer ID and date range ");
		}
		return droneOrderList;
	}

	public DroneOrderDTO getDroneOrderFarmerIdAndId(Long farmerId, Long id) {
		farmerService.findById(farmerId);
		DroneOrder droneOrder = droneOrderRepository.findDroneOrderByFarmerIdAndId(farmerId, id)
				.orElseThrow(() -> new NoSuchElementExistException(
						new StringBuilder("Drone order doesn't exist for id  : ").append(id).append(" with this farmer id : ").append(farmerId).toString()));

		return getDroneOrderDTO(droneOrder);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<DroneOrderDTO> findAll() {
		return null;
	}

	private DroneOrder getDroneOrder(DroneOrderDTO droneOrderDTO) {
		DroneOrder droneOrder = modelMapper.map(droneOrderDTO, DroneOrder.class);

		return droneOrder;
	}

	private DroneOrderDTO getDroneOrderDTO(DroneOrder droneOrder) {
		DroneOrderDTO droneOrderDTO = modelMapper.map(droneOrder, DroneOrderDTO.class);

		return droneOrderDTO;
	}
}