package com.kisaan.jai.service.impl;

import com.kisaan.jai.dto.LoginDetailDTO;
import com.kisaan.jai.entity.Farmer;
import com.kisaan.jai.entity.LoginDetail;
import com.kisaan.jai.exception.InvalidOTPException;
import com.kisaan.jai.exception.MobileNumNotFoundException;
import com.kisaan.jai.repository.FarmerRepository;
import com.kisaan.jai.repository.LoginRepository;
import com.kisaan.jai.service.LoginDetailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class LoginDetailServiceImpl implements LoginDetailService {

	private LoginRepository loginRepository;
	private FarmerRepository farmerRepository;

	@Override
	public String addMobile(String mobile) {
		LoginDetail data = loginRepository.findByMobile(mobile);
		int otpp = ((int)(Math.random()*9000)+1000);
		String otp=String.valueOf(otpp);
		LoginDetail loginData = new LoginDetail(mobile, otp);
		if (data == null) {
			loginRepository.save(loginData);
		} else {
			loginData.setId(data.getId());
			loginRepository.save(loginData);
		}
		return otp;
	}

	@Override
	public LoginDetailDTO loginDetails(LoginDetailDTO loginDetailDto) {
		boolean isFailure = false;
		String message = null;
		RuntimeException ex = null;
		LoginDetail data = loginRepository.findByMobile(loginDetailDto.getMobile());

		if(ObjectUtils.isEmpty(data)){
			isFailure = true;
			message = "Please generate new OTP.";
		}
		
		if(!(data.getOtp().equals(loginDetailDto.getOtp()))) {
			isFailure = true;
			message = "Invalid OTP.";
		}

		Optional<Farmer> farmer = farmerRepository.findByMobileAndIsActive(loginDetailDto.getMobile(), true);
		if(!isFailure) {
			if (farmer.isPresent()) {
				LoginDetailDTO loginDetailDTO = new LoginDetailDTO();
				loginDetailDTO.setFarmerId(farmer.get().getId());

				return loginDetailDTO;
			} else {
				ex = MobileNumNotFoundException.builder()
						.message("OTP Verified")
						.farmerId("")
						.registered(Boolean.FALSE.toString()).build();
			}
		} else {
			if (farmer.isPresent()) {
				ex = InvalidOTPException.builder()
						.farmerId(farmer.get().getId().toString())
						.message(message)
						.registered(Boolean.TRUE.toString()).build();
			} else {
				ex = InvalidOTPException.builder()
						.message(message)
						.farmerId("")
						.registered(Boolean.FALSE.toString()).build();
			}
		}

		throw ex;
	}

}