package com.dev.service.impl;

import com.dev.model.CompanyProfile;
import com.dev.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dev.repository.CompanyRepo;

@Service
public class CompanyProfileImpl implements CompanyService {

    @Autowired
    private CompanyRepo companyRepo;

    @Override
    public CompanyProfile createCompany(CompanyProfile companyProfile) {
        return this.companyRepo.save(companyProfile);
    }
}
