package com.rentmycar.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentmycar.custom_exception.ApiException;
import com.rentmycar.dto.RegisterUserReqDto;
import com.rentmycar.dto.RegisterUserWithDlReqDto;
import com.rentmycar.dto.SignInRequestDto;
import com.rentmycar.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> logInUser(@RequestBody @Valid SignInRequestDto signInRequestDto) {
		System.out.println("in Log in " + signInRequestDto);
		return ResponseEntity.ok(userService.authenticateUser(signInRequestDto));
	}

	// register User with basic details
	@PostMapping("/register_basic")
	public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterUserReqDto registerUserReqDto) {

		return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(registerUserReqDto)
				.orElseThrow(() -> new ApiException("internal server error")));

	}

	// register User with driving license
	@PostMapping("/register_with_dl")
	public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterUserWithDlReqDto registerUserWithDlReqDto) {

		return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(registerUserWithDlReqDto)
				.orElseThrow(() -> new ApiException("internal server error")));
	}

}
