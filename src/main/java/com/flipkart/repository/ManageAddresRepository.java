package com.flipkart.repository;

import com.flipkart.Model.ManageAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManageAddresRepository extends JpaRepository<ManageAddress,Long> {
}
