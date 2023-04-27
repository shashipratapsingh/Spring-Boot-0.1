package com.Zee5blog.Zee5.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.exception.DataException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Zee5blog.Zee5.payloads.ApiResponse;
import com.Zee5blog.Zee5.payloads.UserDto;
import com.Zee5blog.Zee5.services.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	// private static final Logger LOGGER = Logger.getLogger(UserController.class);

	// find user by Id
	@GetMapping("/{id}")
	ResponseEntity<UserDto> user(@PathVariable int id) {

		return ResponseEntity.ok(this.userService.getUserById(id));

	}

	// find All users
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> users() {
		return ResponseEntity.ok(this.userService.getAllUsers());

	}

	// create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		UserDto createUser = this.userService.crateUser(userDto);
		return new ResponseEntity<>(createUser, HttpStatus.CREATED);
	}

	// update
	@PutMapping("/{UserId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer UserId) {
		UserDto updatedUser = this.userService.updateUser(userDto, UserId);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
		this.userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse(), HttpStatus.OK);
	}

}
