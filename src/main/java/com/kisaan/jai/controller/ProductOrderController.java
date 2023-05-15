package com.kisaan.jai.controller;

import com.kisaan.jai.dto.FarmerIdDTO;
import com.kisaan.jai.dto.OrderIdDTO;
import com.kisaan.jai.dto.ProductOrderDTO;
import com.kisaan.jai.exception.InvalidDateRangeException;
import com.kisaan.jai.payload.response.ApiResponse;
import com.kisaan.jai.payload.response.ResponseHandler;
import com.kisaan.jai.service.ProductOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/farmer/order/product")
@RequiredArgsConstructor
@Slf4j
public class ProductOrderController {

	@NotNull
	private final ProductOrderService productOrderService;
	
	@PostMapping
	public ResponseEntity<Object> addProductOrder(@RequestBody ProductOrderDTO productOrderDto){
		log.info("inside the controller add  method");
		ProductOrderDTO order = this.productOrderService.addProductOrder(productOrderDto);
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().message("Saved Successfully.").status(true).orderId(order.getId().toString()).httpStatus(HttpStatus.CREATED).build());
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<Object> getProductOrders(@Valid @RequestBody FarmerIdDTO farmerIDDTO){
		log.info("inside the controller find all method");
		List<ProductOrderDTO> order = this.productOrderService.getProductOrders(farmerIDDTO.getFarmerId());
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(order).count(order.size()).httpStatus(HttpStatus.OK).build());
	}
	
	@GetMapping
	public ResponseEntity<Object> getProductOrder(@Valid @RequestBody OrderIdDTO orderIdDTO){
		log.info("inside the controller find by id method");
		ProductOrderDTO order = this.productOrderService.getProductOrder(orderIdDTO.getFarmerId(),orderIdDTO.getOrderId());
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(order).httpStatus(HttpStatus.OK).build());
	}
	
	@PutMapping
	public ResponseEntity<Object> updateProductOrder(@Valid @RequestBody ProductOrderDTO productOrderDto) {
		log.info("inside the controller update method");
		ProductOrderDTO order = this.productOrderService.updateProductOrder(productOrderDto.getFarmerId(), productOrderDto.getId(), productOrderDto);
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().message("Updated Successfully.").orderId(order.getId().toString()).httpStatus(HttpStatus.OK).build());
	}
	
	@GetMapping("/history")
	public ResponseEntity<Object> getAllOrder(@RequestParam ("farmer_id") Long farmerId,
											  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> startDate,
											  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> endDate) {
		List<ProductOrderDTO> productOrderList;

		if(startDate.isPresent() && !ObjectUtils.isEmpty(startDate.get())) {
				LocalDate tempEndDate = endDate.orElse(LocalDate.now());

			if(startDate.get().isAfter(tempEndDate)) {
				throw new InvalidDateRangeException("Invalid date range.");
			}

			productOrderList = productOrderService.getAllOrderByFarmerIdAndDateRange(farmerId,
					startDate.get().atStartOfDay().atZone(ZoneId.systemDefault()),
					tempEndDate.atStartOfDay().atZone(ZoneId.systemDefault()).plusDays(1));
		} else {
			productOrderList = productOrderService.getAllOrderByFarmerId(farmerId);
		}
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(productOrderList).count(productOrderList.size()).httpStatus(HttpStatus.OK).build());
	}
}