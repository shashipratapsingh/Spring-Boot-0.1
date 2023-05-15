package com.kisaan.jai.controller;

import com.kisaan.jai.dto.ZoomConsultantDTO;
import com.kisaan.jai.payload.response.ApiResponse;
import com.kisaan.jai.payload.response.ResponseHandler;
import com.kisaan.jai.service.ZoomService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/consultant")
@Slf4j
@AllArgsConstructor
public class ZoomConsultantController {

	private ZoomService zoomConsultantService;
	
	@PostMapping
	public ResponseEntity<Object> addConsultant(@Valid @RequestBody ZoomConsultantDTO consultantDto){
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(zoomConsultantService.save(consultantDto)).httpStatus(HttpStatus.CREATED).build());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getConsultant(@PathVariable Long id){
		ZoomConsultantDTO consultantDto = this.zoomConsultantService.findById(id);
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(consultantDto).httpStatus(HttpStatus.OK).build());
	}
	
	@GetMapping("/all")
	public ResponseEntity<Object> getAllConsultant(){
		List<ZoomConsultantDTO> consultantDto = this.zoomConsultantService.findAll();
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(consultantDto).count(consultantDto.size()).httpStatus(HttpStatus.OK).build());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateConsultant(@PathVariable Long id,@Valid @RequestBody ZoomConsultantDTO dto){
		ZoomConsultantDTO update = this.zoomConsultantService.update(id, dto);
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(update).httpStatus(HttpStatus.OK).build());
	}
}
