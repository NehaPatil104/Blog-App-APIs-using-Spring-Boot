package com.blog.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.UserDto;
import com.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// POST - create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		
		// Call the create user method of service class
		UserDto createUserDto = this.userService.createUser(userDto);
		
		// Return the created entity with http status
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}
	
	// PUT - update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer userId){
		
		// Call the update user method of service class
		UserDto updatedUser = this.userService.updateUser(userDto, userId);
		
		// Return OK status
		return ResponseEntity.ok(updatedUser);
	}
	
	// DELETE - delete user
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
		
		// Call the delete method of service class
		this.userService.deleteUser(uid);
		
		// Return the delete message
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
	}
	
	// GET - get user
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		
		// Call the getAllUsers method and return the users list.
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	

	// GET - get specific user
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
		
		// Call the getUserById method and return single user.
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
	
}