package com.kisaan.jai.controller;

import com.kisaan.jai.dto.DroneOrderDTO;
import com.kisaan.jai.dto.FarmerIdDTO;
import com.kisaan.jai.dto.OrderIdDTO;
import com.kisaan.jai.exception.InvalidDateRangeException;
import com.kisaan.jai.payload.response.ApiResponse;
import com.kisaan.jai.payload.response.ResponseHandler;
import com.kisaan.jai.service.DroneOrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/farmer/order/drone")
@Slf4j
@AllArgsConstructor
public class DroneOrderController {

	@Autowired
	private DroneOrderService droneOrderService;

	@PostMapping
	public ResponseEntity<Object> addDroneOrder(@Valid @RequestBody DroneOrderDTO droneOrderDto){
		log.info("inside the drone order controller add method");
		DroneOrderDTO orderDto = droneOrderService.save(droneOrderDto);

		return ResponseHandler.prepareResponse(
				ApiResponse.builder().status(true).message("Saved successfully").orderId(orderDto.getId().toString()).httpStatus(HttpStatus.OK).message("Drone order created successfully.").build());
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<Object> getAllDroneOrders(@Valid @RequestBody FarmerIdDTO farmerIdDTO){
		List<DroneOrderDTO> droneOrderDTO = droneOrderService.getAllByFarmerId(farmerIdDTO.getFarmerId());

		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(droneOrderDTO).status(true).message("Fetch successfully").httpStatus(HttpStatus.OK).count(droneOrderDTO.size()).build());
	}
	
	@GetMapping
	public ResponseEntity<Object> getDroneOrder(@Valid @RequestBody OrderIdDTO orderIdDTO){
		DroneOrderDTO droneOrderDto = droneOrderService.getDroneOrderFarmerIdAndId(orderIdDTO.getFarmerId(), orderIdDTO.getOrderId());

		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(droneOrderDto).status(true).message("Fetch Data").httpStatus(HttpStatus.OK).build());
	}
	
	
	@PutMapping
	public ResponseEntity<Object> getDroneOrder(@Valid @RequestBody DroneOrderDTO droneOrderDTO){
		DroneOrderDTO droneOrderDto = droneOrderService.update(droneOrderDTO.getId(), droneOrderDTO);

		return ResponseHandler.prepareResponse(
				ApiResponse.builder().status(true).message("Updated Successfully").orderId(droneOrderDto.getId().toString()).httpStatus(HttpStatus.OK).message("Drone order updated successfully.").build());
	}

	@GetMapping("/history")
	public ResponseEntity<Object> getAllDroneOrder(@RequestParam Long farmerId,
												   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> startDate,
												   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> endDate){
		List<DroneOrderDTO> droneOrderList;

		if(startDate.isPresent() && !ObjectUtils.isEmpty(startDate.get())) {
			LocalDate tempEndDate = endDate.orElse(LocalDate.now());

			if(startDate.get().isAfter(tempEndDate)) {
				throw new InvalidDateRangeException("Invalid date range.");
			}

			droneOrderList = droneOrderService.getAllByFarmerIdAndDateRange(farmerId,
					startDate.get().atStartOfDay().atZone(ZoneId.systemDefault()),
					tempEndDate.atStartOfDay().atZone(ZoneId.systemDefault()).plusDays(1));
		} else {
			droneOrderList = droneOrderService.getAllByFarmerId(farmerId);
		}

		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(droneOrderList).status(true).message("Fetch successfully").httpStatus(HttpStatus.OK).count(droneOrderList.size()).build());
	}
}
