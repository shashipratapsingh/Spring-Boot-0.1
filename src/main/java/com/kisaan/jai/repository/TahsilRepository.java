package com.kisaan.jai.repository;

import com.kisaan.jai.entity.Tahsil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TahsilRepository extends JpaRepository<Tahsil, Long> {
}
