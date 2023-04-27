package com.HotelService.service.impl;

import com.HotelService.DTO.RegistoryCompanyDto;
import com.HotelService.entities.RegisterCompany;
import com.HotelService.repository.CompanyRepository;
import com.HotelService.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CompanyServiceImpl implements  CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ModelMapper modelMapper;


    //Create Profile
    public RegistoryCompanyDto registrationCompany(RegistoryCompanyDto registoryCompanyDto) {
        RegisterCompany registerCompany = this.dtoToUser(registoryCompanyDto);
        RegisterCompany createCompany = this.companyRepository.save(registerCompany);
        return this.userToDto(createCompany);
    }

    // Find the company profile By ID

    public Optional<RegistoryCompanyDto> findCompanyDetail(Integer registrationId) {
        RegisterCompany registerCompany = this.companyRepository.findById(registrationId).get();
        System.out.println("shashi----->"+registerCompany);
        return Optional.ofNullable(userToDto(registerCompany));
    }

    @Override
    public Optional<RegistoryCompanyDto> findCompanyDetailByCompanyName(String CompanyName) {
        return Optional.empty();
    }


    public RegisterCompany findByCompanyName(String companyName){
        return this.companyRepository.findByCompanyName(companyName);
}























    // DTO Conversion
    public RegistoryCompanyDto userToDto(RegisterCompany user) {
        RegistoryCompanyDto userDto = this.modelMapper.map(user, RegistoryCompanyDto.class);
        return userDto;
    }

    public RegisterCompany dtoToUser(RegistoryCompanyDto registoryCompanyDto) {
        RegisterCompany registerCompany = this.modelMapper.map(registoryCompanyDto, RegisterCompany.class);
        return registerCompany;
    }

}
