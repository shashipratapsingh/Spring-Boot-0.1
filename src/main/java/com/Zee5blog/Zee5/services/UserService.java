package com.Zee5blog.Zee5.services;

import java.util.List;

import com.Zee5blog.Zee5.payloads.UserDto;
import com.Zee5blog.Zee5.payloads.UserResponse;

public interface UserService {

	UserDto crateUser(UserDto user);

	UserDto updateUser(UserDto user, Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);

	

}
