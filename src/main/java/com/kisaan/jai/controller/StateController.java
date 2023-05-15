package com.kisaan.jai.controller;

import com.kisaan.jai.dto.StateDTO;
import com.kisaan.jai.payload.response.ApiResponse;
import com.kisaan.jai.payload.response.ResponseHandler;
import com.kisaan.jai.service.StateService;
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
@RequestMapping("/v1/state")
@Slf4j
public class StateController {

	@Autowired
	private StateService stateService;
	
	@GetMapping("/all")
	public ResponseEntity<Object> getAllState() {
		log.info("Invoked StateController getAllState method.");
		List<StateDTO> stateList = stateService.findAll();
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(stateList).count(stateList.size()).httpStatus(HttpStatus.OK).build());
	}

	@GetMapping("/{stateId}")
	public ResponseEntity<Object> getState(@PathVariable Long stateId) {
		log.debug("Invoked StateController getState method, stateId : {}", stateId);
		StateDTO stateDTO = stateService.findById(stateId);
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(stateDTO).httpStatus(HttpStatus.OK).build());
	}
}
