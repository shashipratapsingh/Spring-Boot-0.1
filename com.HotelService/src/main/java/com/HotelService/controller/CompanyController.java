package com.HotelService.controller;

import com.HotelService.DTO.RegistoryCompanyDto;
import com.HotelService.entities.RegisterCompany;
import com.HotelService.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    @PostMapping(value = "/createCompanyDetails")
    public ResponseEntity<RegistoryCompanyDto> createCompanyProfile(@Valid @RequestBody RegistoryCompanyDto registoryCompanyDto) {
        RegistoryCompanyDto registryCompanyDto1 = this.companyService.registrationCompany(registoryCompanyDto);
        return new ResponseEntity<>(registryCompanyDto1, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{registrationId}")
    public RegistoryCompanyDto findCompanyDetail(@PathVariable Integer registrationId) {
        return this.companyService.findCompanyDetail(registrationId).get();
    }


    @GetMapping(value = "/companyName/{companyName}")
    public RegisterCompany findByCompanyName(String companyName) {
        System.out.println("hey--> ");
        return this.companyService.findByCompanyName(companyName);
    }

    @GetMapping(value = "/companyName1/{companyName}")
    public RegisterCompany findByCompanyName1(String companyName) {
        return this.companyService.findByCompanyName(companyName);
    }


}
