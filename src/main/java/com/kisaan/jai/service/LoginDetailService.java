package com.kisaan.jai.service;

import com.kisaan.jai.dto.LoginDetailDTO;
import com.kisaan.jai.entity.LoginDetail;

public interface LoginDetailService {

	String addMobile(String mobile);

	LoginDetailDTO loginDetails(LoginDetailDTO loginDetailDto);
		
}