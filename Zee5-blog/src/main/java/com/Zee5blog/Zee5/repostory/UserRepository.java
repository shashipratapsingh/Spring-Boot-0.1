package com.Zee5blog.Zee5.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Zee5blog.Zee5.models.User;
import com.Zee5blog.Zee5.payloads.UserDto;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	void save(UserDto user);
	

}
