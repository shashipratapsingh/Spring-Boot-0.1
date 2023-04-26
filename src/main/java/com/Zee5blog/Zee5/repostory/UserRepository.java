package com.Zee5blog.Zee5.repostory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Zee5blog.Zee5.models.Post;
import com.Zee5blog.Zee5.models.User;
import com.Zee5blog.Zee5.payloads.UserDto;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	void save(UserDto user);
	
	public List<User> findAll();
	
	//login
	
	Optional<User> findByEmail(String email);
	
	

}
