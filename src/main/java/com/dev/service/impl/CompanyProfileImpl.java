package com.dev.service.impl;

import com.dev.model.CompanyProfile;
import com.dev.service.CompanyService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dev.repository.CompanyRepo;

import java.util.logging.Logger;

@Service
public class CompanyProfileImpl implements CompanyService {

    @Autowired
    private CompanyRepo companyRepo;

    @Override
    public CompanyProfile createCompany(CompanyProfile companyProfile) {
        Logger logger= (Logger) LoggerFactory.getLogger(CompanyProfileImpl.class);
        return this.companyRepo.save(companyProfile);
    }
}
