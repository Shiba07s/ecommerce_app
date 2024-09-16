package com.userService.services;

import java.util.List;

import com.userService.Payload.UserDto;
import com.userService.exception.UserNotFoundException;

public interface UserService {
	
	UserDto createNewUser(UserDto userDto);
	
	List<UserDto> getAllUserDetails();
	
	UserDto getUserDetailsById(long userId) throws UserNotFoundException ;
	
	UserDto updateUserDetails(long userId,UserDto userDto) throws UserNotFoundException;
	
	void deleteUsersDetails(long userId) throws UserNotFoundException;

}
