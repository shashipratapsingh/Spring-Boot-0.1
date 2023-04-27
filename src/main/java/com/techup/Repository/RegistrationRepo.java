package com.techup.Repository;

import org.springframework.boot.autoconfigure.batch.BatchProperties.Jdbc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;


import com.techup.entites.RegistrationEntity;

public interface RegistrationRepo extends JpaRepository<RegistrationEntity, Integer>{

}
