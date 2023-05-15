package com.kisaan.jai.controller;

import com.kisaan.jai.dto.VillageDTO;
import com.kisaan.jai.dto.VillageMetaDataDTO;
import com.kisaan.jai.payload.response.ApiResponse;
import com.kisaan.jai.payload.response.ResponseHandler;
import com.kisaan.jai.service.VillageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/village")
@Slf4j
public class VillageController {

	@Autowired
	private VillageService villageService;
	
	@GetMapping("/all")
	public ResponseEntity<Object> getAllVillage(@Valid @RequestBody VillageMetaDataDTO villageMetaDataDTO) {
		log.info("Invoked VillageController getAllVillage method.");
		List<VillageDTO> villageList = villageService.getVillagesByMetaData(villageMetaDataDTO);

		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(villageList).message("Village fetched successfully").httpStatus(HttpStatus.OK).build());
	}

	@GetMapping("/{villageId}")
	public ResponseEntity<Object> getVillage(@PathVariable Long villageId) {
		log.debug("Invoked VillageController getVillage method, villageId : {}", villageId);
		VillageDTO villageDTO = villageService.findById(villageId);
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().responseBody(villageDTO).httpStatus(HttpStatus.OK).build());
	}
}
