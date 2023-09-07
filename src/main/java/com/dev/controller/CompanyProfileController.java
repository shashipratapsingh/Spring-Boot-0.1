package com.dev.controller;

import com.dev.model.CompanyProfile;
import com.dev.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyProfileController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/profile")
    public CompanyProfile createCompany(@RequestBody CompanyProfile companyProfile) {
        return this.companyService.createCompany(companyProfile);
    }



}
