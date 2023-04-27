package com.Zee5blog.Zee5.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Zee5blog.Zee5.models.User;
import com.Zee5blog.Zee5.payloads.UserDto;
import com.Zee5blog.Zee5.repostory.UserRepository;
import com.Zee5blog.Zee5.services.UserService;
import com.Zee5blog.Zee5.exception.*;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto crateUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepository.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());

		User user1 = this.userRepository.save(user);

		UserDto userDto1 = this.userToDto(user1);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepository.findById(userId).orElse(null);
		UserDto userDto = this.userToDto(user);
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepository.findAll();
		List<UserDto> userDto = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		this.userRepository.delete(user);
	}

	// Dto Conversion

	private User dtoToUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);
		/*
		 * User user = new User(); user.setId(userDto.getId());
		 * user.setName(userDto.getName()); user.setEmail(userDto.getEmail());
		 * user.setAbout(userDto.getAbout()); user.setPassword(userDto.getPassword());
		 */

		return user;

	}

	public UserDto userToDto(User user) {

		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		/*
		 * UserDto userDto = new UserDto(); userDto.setId(user.getId());
		 * userDto.setName(user.getName()); userDto.setEmail(user.getEmail());
		 * userDto.setPassword(user.getPassword()); userDto.setAbout(user.getAbout());
		 */
		return userDto;

	}

}
