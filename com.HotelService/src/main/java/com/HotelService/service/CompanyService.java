package com.HotelService.service;

import com.HotelService.DTO.RegistoryCompanyDto;
import com.HotelService.entities.RegisterCompany;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    RegistoryCompanyDto registrationCompany(RegistoryCompanyDto registoryCompanyDto);

    Optional<RegistoryCompanyDto> findCompanyDetail(Integer registrationId);



    Optional<RegistoryCompanyDto> findCompanyDetailByCompanyName(String CompanyName);

    RegisterCompany findByCompanyName(String companyName);

    //second





}
