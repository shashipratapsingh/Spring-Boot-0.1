package com.kisaan.jai.controller;

import com.kisaan.jai.dto.FarmerIdDTO;
import com.kisaan.jai.dto.OrderSoilDTO;
import com.kisaan.jai.dto.OrderSoilIdDTO;
import com.kisaan.jai.exception.InvalidDateRangeException;
import com.kisaan.jai.payload.response.ApiResponse;
import com.kisaan.jai.payload.response.ResponseHandler;
import com.kisaan.jai.service.OrderSoilService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/v1/farmer/order/soil")
@AllArgsConstructor
@Slf4j
public class OrderSoilController {

	private OrderSoilService orderSoilService;
	
	@PostMapping
	public ResponseEntity<Object> addOrderSoil(@Valid @RequestBody OrderSoilDTO orderSoilDto){
		orderSoilDto.setFarmerId(orderSoilDto.getFarmerId());
		OrderSoilDTO soilDto = orderSoilService.save(orderSoilDto);
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().status(true).message("Your Order Placed Successfully").orderId(soilDto.getId().toString()).httpStatus(HttpStatus.CREATED).build());
	}

	@GetMapping("/all")
	public ResponseEntity<Object> getAllSoil(@Valid @RequestBody FarmerIdDTO farmerIDDTO){
		List<OrderSoilDTO> soilDtos = this.orderSoilService.getAllByFarmerId(farmerIDDTO.getFarmerId());
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(soilDtos).count(soilDtos.size()).httpStatus(HttpStatus.OK).build());
	}
	
	@GetMapping
	public ResponseEntity<Object> getSoil(@Valid @RequestBody OrderSoilIdDTO orderSoilIdDTO){
		OrderSoilDTO orderSoilDto = this.orderSoilService.getByFarmerIdAndId(orderSoilIdDTO.getFarmerId(), orderSoilIdDTO.getOrderId());
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(orderSoilDto).httpStatus(HttpStatus.OK).build());
	}

	@PutMapping
	public ResponseEntity<Object> updateSoil(@Valid @RequestBody OrderSoilDTO orderSoilDto){
//		orderSoilDto.setFarmerId(orderSoilDto.getFarmerId());
		orderSoilDto = this.orderSoilService.update(orderSoilDto.getId(), orderSoilDto);
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().message("Order updated Successfully").orderId(orderSoilDto.getId().toString()).httpStatus(HttpStatus.OK).build());
	}
	
	@GetMapping("/history")
	public ResponseEntity<Object> getAllByFarmer(@RequestParam("farmer_id") Long farmerId,
												 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> startDate,
												 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> endDate){
		List<OrderSoilDTO> soilOrderList;
		if(startDate.isPresent() && !ObjectUtils.isEmpty(startDate.get())) {
			LocalDate tempEndDate = endDate.orElse(LocalDate.now());

			if(startDate.get().isAfter(tempEndDate)) {
				throw new InvalidDateRangeException("Invalid date range.");
			}

			soilOrderList = orderSoilService.getAllByFarmerIdAndDateRange(farmerId,
					startDate.get().atStartOfDay().atZone(ZoneId.systemDefault()),
					tempEndDate.atStartOfDay().atZone(ZoneId.systemDefault()).plusDays(1));
		} else {
			soilOrderList = orderSoilService.getAllByFarmerId(farmerId);
		}

		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(soilOrderList).count(soilOrderList.size()).httpStatus(HttpStatus.OK).build());
	}
}
