package com.dev.repository;

import com.dev.model.CompanyProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo  extends JpaRepository<CompanyProfile,Integer> {
}
