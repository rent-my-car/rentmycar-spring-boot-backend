package com.rentmycar.controller;

import javax.validation.Valid;
import javax.validation.constraints.Max;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import com.rentmycar.dto.SignInRequestDto;
import com.rentmycar.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;

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
	
	@GetMapping("guest/profile/{guestId}")
	@Operation(description = "getProfileDetails By Id")
	public ResponseEntity<?> getGuestProfileDetails(@PathVariable @Max(100) Long guestId){
		System.out.println("In Guest Profile" + guestId);		
		return ResponseEntity.ok(userService.getGuestProfileDetails(guestId));
	}
}
