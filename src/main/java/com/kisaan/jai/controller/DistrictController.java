package com.kisaan.jai.controller;

import com.kisaan.jai.dto.DistrictDTO;
import com.kisaan.jai.payload.response.ApiResponse;
import com.kisaan.jai.payload.response.ResponseHandler;
import com.kisaan.jai.service.DistrictService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/district")
@Slf4j
public class DistrictController {

	@Autowired
	private DistrictService districtService;
	
	@GetMapping("/all")
	public ResponseEntity<Object> getAllDistrict() {
		log.info("Invoked DistrictController getAllDistrict method.");
		List<DistrictDTO> districtList = districtService.findAll();
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(districtList).httpStatus(HttpStatus.OK).count(districtList.size()).build());
	}

	@GetMapping("/{districtId}")
	public ResponseEntity<Object> getDistrict(@PathVariable Long districtId) {
		log.debug("Invoked DistrictController getDistrict method, districtId : {}", districtId);
		DistrictDTO districtDTO = districtService.findById(districtId);
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(districtDTO).httpStatus(HttpStatus.OK).build());
	}
}
