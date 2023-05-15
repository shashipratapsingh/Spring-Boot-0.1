package com.kisaan.jai.repository;

import com.kisaan.jai.entity.LoginDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginDetail, Long> {
    LoginDetail findByMobile(String mobile);
}