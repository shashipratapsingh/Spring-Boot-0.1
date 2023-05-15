package com.kisaan.jai.repository;

import com.kisaan.jai.entity.Videos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideosRepository extends JpaRepository<Videos, Long>{
	
	Optional<Videos> findByName(String fileName);

}
