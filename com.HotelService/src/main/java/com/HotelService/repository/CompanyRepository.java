package com.HotelService.repository;

import com.HotelService.entities.RegisterCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<RegisterCompany,Integer> {
    RegisterCompany findByCompanyName(String companyName);




}
