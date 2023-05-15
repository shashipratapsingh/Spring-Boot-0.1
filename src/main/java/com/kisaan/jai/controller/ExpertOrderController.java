package com.kisaan.jai.controller;

import com.kisaan.jai.dto.ExpertOrderDTO;
import com.kisaan.jai.payload.response.ApiResponse;
import com.kisaan.jai.payload.response.ResponseHandler;
import com.kisaan.jai.service.ExpertOrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/v1/farmer/order/expert")
@AllArgsConstructor
public class ExpertOrderController {

	private ExpertOrderService exertOrderService;
	
	@PostMapping
	public ResponseEntity<Object> addExpertOrder(@PathVariable Long farmerId, @Valid @RequestBody ExpertOrderDTO expertOrderDto){
		ExpertOrderDTO expertOrder =this.exertOrderService.save(expertOrderDto);
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().orderId(expertOrder.getExpertId().toString()).message("Saved Successfully.").httpStatus(HttpStatus.CREATED).build());
	}
	
	
	@GetMapping("/{expertId}/all")
	public ResponseEntity<Object> getAllExperts(@PathVariable Long farmerId, @PathVariable Long expertId){
		List<ExpertOrderDTO> list = exertOrderService.getAllByFarmerIdAndExpertId(farmerId, expertId);
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(list).count(list.size()).httpStatus(HttpStatus.OK).build());
	}
	
	@GetMapping("/{expertId}/{id}")
	public ResponseEntity<Object> getExperts(@PathVariable Long id, @PathVariable Long farmerId, @PathVariable Long expertId){
		ExpertOrderDTO expertOrderDto =this.exertOrderService.getExpertOrderIdAndFarmerIdAndExpertId(id, farmerId, expertId);
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(expertOrderDto).httpStatus(HttpStatus.OK).build());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> getExperts(@PathVariable Long id, @RequestBody ExpertOrderDTO dto){
		ExpertOrderDTO expertOrderDto = exertOrderService.update(id, dto);
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().orderId(expertOrderDto.getExpertId().toString())
						.message("Expert order updated successfully.").httpStatus(HttpStatus.OK).build());
	}
}
