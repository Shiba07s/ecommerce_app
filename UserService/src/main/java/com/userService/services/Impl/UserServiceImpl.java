package com.userService.services.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.userService.Payload.UserDto;
import com.userService.entities.User;
import com.userService.exception.UserNotFoundException;
import com.userService.repositories.UserRepository;
import com.userService.services.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final ModelMapper modelMapper;

	@Override
	public UserDto createNewUser(UserDto userDto) {
		
		User map = modelMapper.map(userDto, User.class);
		User save = userRepository.save(map);
		return modelMapper.map(save, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUserDetails() {
		List<User> all = userRepository.findAll();
 		return all.stream().map(users -> modelMapper.map(users, UserDto.class)).collect(Collectors.toList());
	}

	@Override
	public UserDto updateUserDetails(long userId, UserDto userDto) throws UserNotFoundException {
		
		Optional<User> userById = userRepository.findById(userId);
		if (userById.isPresent()) {
			
			User user = userById.get();
			System.out.println("user Id value: "+user);
			user.setName(userDto.getName());
			user.setEmail(userDto.getEmail());
			user.setAddress(userDto.getAddress());
			user.setPhoneNumber(userDto.getPhoneNumber());
			
			User save = userRepository.save(user);
			return modelMapper.map(save, UserDto.class);
			
		} else {
			throw new UserNotFoundException("user not with this id : "+userId);
		}
	}

	@Override
	public void deleteUsersDetails(long userId) throws UserNotFoundException {
		
		Optional<User> optional = userRepository.findById(userId);
		if (optional.isPresent()) {
			User user = optional.get();
			
			userRepository.delete(user);
			
		} else {
	        throw new UserNotFoundException("User with ID " + userId + " not found.");

		}

	}

	@Override
	public UserDto getUserDetailsById(long userId) throws UserNotFoundException {
	 Optional<User> byId = userRepository.findById(userId);
	 if (byId.isPresent()) {
		User user = byId.get();
		return modelMapper.map(user, UserDto.class);
 	} else {
        throw new UserNotFoundException("User with ID " + userId + " not found.");
	} 
	}

}
