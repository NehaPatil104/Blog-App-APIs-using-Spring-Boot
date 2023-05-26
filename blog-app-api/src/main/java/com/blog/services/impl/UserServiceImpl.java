package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.UserDto;
import com.blog.repositories.UserRepo;
import com.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		// Save user DTO to user entity
		User user = this.dtoToUser(userDto);
		
		// Save user call
		User savedUser = this.userRepo.save(user);
		
		// Return the converted user to dto entity
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		// Get the user from the db
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", " Id ", userId));
		
		// Update the user with the values in the dto user
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		// Save the updated user entity into db
		User updatedUser = this.userRepo.save(user);
		
		// Convert the user entity to dto
		UserDto userDto1 = this.userToDto(updatedUser);
		
		// Return dto
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		// Call th get the user from the db
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", " Id ", userId));
		
		// Return the user to dto
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		// Call the find all the users
		List<User> users = this.userRepo.findAll();
		
		// Lambda function to store the users into map
		List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		// Find the user by id to delete
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", " Id ", userId));
		
		// Call the delete method 
		this.userRepo.delete(user);
	}
	
	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);		
		return user;
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}
}
