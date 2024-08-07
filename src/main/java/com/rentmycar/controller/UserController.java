package com.rentmycar.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentmycar.dto.SignInRequestDto;
import com.rentmycar.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@PostMapping("/login")
	public ResponseEntity<?> logInUser(@RequestBody @Valid SignInRequestDto signInRequestDto){
		System.out.println("in Log in " + signInRequestDto);
		
		return ResponseEntity.ok(userService.authenticateUser(signInRequestDto));
	}
}
