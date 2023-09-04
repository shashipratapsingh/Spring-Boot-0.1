package com.dev.repository;

import com.dev.model.Emails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Emails,Integer> {
}
