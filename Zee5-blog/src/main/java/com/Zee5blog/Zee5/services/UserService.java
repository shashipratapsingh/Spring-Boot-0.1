package com.Zee5blog.Zee5.services;

import java.util.List;

import com.Zee5blog.Zee5.payloads.UserDto;

public interface UserService {

	UserDto crateUser(UserDto user);

	UserDto updateUser(UserDto user, Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer UserId);

}
