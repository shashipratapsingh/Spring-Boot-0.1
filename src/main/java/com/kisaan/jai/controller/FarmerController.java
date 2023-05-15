package com.kisaan.jai.controller;

import com.kisaan.jai.dto.*;
import com.kisaan.jai.payload.response.ApiResponse;
import com.kisaan.jai.payload.response.ResponseHandler;
import com.kisaan.jai.service.DroneOrderService;
import com.kisaan.jai.service.FarmerService;
import com.kisaan.jai.service.OrderSoilService;
import com.kisaan.jai.service.ProductOrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/v1")
public class FarmerController {

	private FarmerService farmerService;
	private DroneOrderService droneOrderService;
	private OrderSoilService orderSoilService;
	private ProductOrderService productOrderService;


	
	@PostMapping("/farmer")
	private ResponseEntity<Object> addFarmer(@Valid @RequestBody FarmerDTO farmerDto){
		log.info("inside the post method in controller");

		FarmerDTO save = farmerService.save(farmerDto);
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().message("Farmer Registered Successfully.")
						.farmerId(save.getId().toString()).httpStatus(HttpStatus.CREATED).build());
	}
	
	
	@GetMapping("/farmers")
	private ResponseEntity<Object> getFarmers(){
		log.info("inside the post method in controller");
		List<FarmerDTO> farmerList = this.farmerService.findAll();

		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(farmerList).httpStatus(HttpStatus.OK).count(farmerList.size()).build());
	}
	
	@GetMapping("/farmer")
	private ResponseEntity<Object> getFarmer(@Valid @RequestBody FarmerIdDTO farmerIdDTO){
		log.info("inside the post method in controller");
		FarmerDTO farmer = this.farmerService.findById(farmerIdDTO.getFarmerId());
		farmer.setPlotId(null);
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().status(true).message("Farmer data").responseBody(farmer).httpStatus(HttpStatus.OK).build());
	}
	
	@DeleteMapping("/farmer")
	private ResponseEntity<Object> deleteFarmer(@Valid @RequestBody FarmerIdDTO farmerIdDTO){
		log.info("inside the post method in controller");
		farmerService.delete(farmerIdDTO.getFarmerId());
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().message("Farmer is deleted successfully").httpStatus(HttpStatus.OK).build());
	}
	
	@PutMapping("/farmer")
	private ResponseEntity<Object> updateFarmer(@Valid @RequestBody FarmerDTO farmerDto){
		log.info("inside the post method in controller");
		FarmerDTO farmer = this.farmerService.update(farmerDto.getId(), farmerDto);
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().message("Farmer updated Successfully.").httpStatus(HttpStatus.OK).build());
	}


	@GetMapping("/allOrders")
	public ResponseEntity<Object> allOrders(@Valid @RequestBody FarmerIdDTO dto){
		List<DroneOrderDTO> dron = droneOrderService.getAllByFarmerId(dto.getFarmerId());
		List<OrderSoilDTO> soil = orderSoilService.getAllByFarmerId(dto.getFarmerId());
		List<ProductOrderDTO> products = productOrderService.getAllOrderByFarmerId(dto.getFarmerId());

		FarmerOrderDetailsDTO detailsDTO= new FarmerOrderDetailsDTO(dron, soil, products);
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().message("Fetched Successfully ").responseBody(detailsDTO).status(true).httpStatus(HttpStatus.OK).build()
		);
	}
}