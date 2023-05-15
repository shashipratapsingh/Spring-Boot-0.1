package com.kisaan.jai.controller;


import com.kisaan.jai.dto.LoginDetailDTO;
import com.kisaan.jai.dto.MobileNumberDTO;
import com.kisaan.jai.payload.response.ApiResponse;
import com.kisaan.jai.payload.response.OtpResponse;
import com.kisaan.jai.payload.response.ResponseHandler;
import com.kisaan.jai.service.LoginDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/login")
@Slf4j
public class LoginDetailController {

	@Autowired
	private LoginDetailService loginDetailService;

	@PostMapping("/mobile")
	public ResponseEntity<Object> takeMobile(@Valid @RequestBody MobileNumberDTO mobile) {
		String otp = loginDetailService.addMobile(mobile.getMobile());
		return ResponseHandler.prepareResponse(
				ApiResponse.builder().message("OTP sent to registered mobile number.")
						.httpStatus(HttpStatus.OK)
						.responseBody(new OtpResponse(otp)).build());
	}
	
	@PostMapping("/data")
	public ResponseEntity<Object> loginDetails(@Valid @RequestBody LoginDetailDTO loginDetailDto) {
		LoginDetailDTO loginDetail = loginDetailService.loginDetails(loginDetailDto);

		return ResponseHandler.prepareResponse(ApiResponse.builder()
				.isRegistered(Boolean.TRUE.toString())
				.message("Otp verified")
				.farmerId(loginDetail.getFarmerId().toString())
				.httpStatus(HttpStatus.OK).build());
	}
	
}