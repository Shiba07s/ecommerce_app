package com.userService.controller;

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

import com.userService.Payload.UserDto;
import com.userService.exception.UserNotFoundException;
import com.userService.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
    // Create a new user
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.createNewUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUserDetails();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") long userId) throws UserNotFoundException {
        UserDto user = userService.getUserDetailsById(userId); // Assume this method exists in UserService
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Update user details by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") long userId, @RequestBody UserDto userDto) throws UserNotFoundException {
        UserDto updatedUser = userService.updateUserDetails(userId, userDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Delete user by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long userId) throws UserNotFoundException {
        userService.deleteUsersDetails(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
