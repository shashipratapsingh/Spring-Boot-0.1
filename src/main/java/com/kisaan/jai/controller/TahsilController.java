package com.kisaan.jai.controller;

import com.kisaan.jai.dto.TahsilDTO;
import com.kisaan.jai.payload.response.ApiResponse;
import com.kisaan.jai.payload.response.ResponseHandler;
import com.kisaan.jai.service.TahsilService;
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
@RequestMapping("/v1/tahsil")
@Slf4j
public class TahsilController {

	@Autowired
	private TahsilService tahsilService;
	
	@GetMapping("/all")
	public ResponseEntity<Object> getAllTahsil() {
		log.info("Invoked TahsilController getAllTahsil method.");
		List<TahsilDTO> tahsilList = tahsilService.findAll();
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(tahsilList).count(tahsilList.size()).httpStatus(HttpStatus.OK).build());
	}

	@GetMapping("/{tahsilId}")
	public ResponseEntity<Object> getTahsil(@PathVariable Long tahsilId) {
		log.debug("Invoked TahsilController getTahsil method, tahsilId : {}", tahsilId);
		TahsilDTO tahsilDTO = tahsilService.findById(tahsilId);
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(tahsilDTO).httpStatus(HttpStatus.OK).build());
	}
}
