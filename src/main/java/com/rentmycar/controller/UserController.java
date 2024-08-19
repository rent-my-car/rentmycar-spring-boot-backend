package com.rentmycar.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentmycar.custom_exception.ApiException;
import com.rentmycar.custom_exception.ResourceNotFoundException;
import com.rentmycar.dto.ApiResponseDto;
import com.rentmycar.dto.RegisterUserReqDto;
import com.rentmycar.dto.RegisterUserWithDlReqDto;
import com.rentmycar.dto.SignInRequestDto;
import com.rentmycar.dto.SignInResponseDto;
import com.rentmycar.dto.UpdateBasicUserDetailsDto;
import com.rentmycar.security.JwtUtils;
import com.rentmycar.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	// method for role based login for user
	@PostMapping("/login")
	public ResponseEntity<?> logInUser(@RequestBody @Valid SignInRequestDto request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.authenticateUser(request));
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

	// get Basic User profile details
	@GetMapping("/profile/{userId}")
	@Operation(description = "getProfileDetails By Id")
	public ResponseEntity<?> getUserProfileDetails(@PathVariable Long userId) {
		System.out.println("In User Profile" + userId);
		return ResponseEntity.ok(userService.getUserProfileDetails(userId));

	}

	// update user basic details
	@PutMapping("/update/{userId}")
	@Operation(description = "Update User Basic Details By Id")
	public ResponseEntity<?> updateBasicUserDetails(@PathVariable Long userId,
			@Valid @RequestBody UpdateBasicUserDetailsDto user) {
		try {
			// Call the service method to update the user details
			Optional<UpdateBasicUserDetailsDto> updatedGuestDto = userService.updateBasicUserDetails(userId, user);

			if (updatedGuestDto.isPresent()) {
				// Successfully updated user details
				return ResponseEntity.ok(userService.updateBasicUserDetails(userId, user));
			} else {
				// This case is not likely to occur due to exception handling in the service
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseDto("User not found with ID: "));
			}
		} catch (ResourceNotFoundException e) {
			// Handle the specific exception for not found resource
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseDto(e.getMessage()));
		} catch (Exception e) {
			// Handle any other unexpected exceptions
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponseDto("An unexpected error occurred: " + e.getMessage()));
		}
	}

	// Soft delete a user
	@PatchMapping("delete/{userId}")
	@Operation(description = "Soft delete a user with userId")
	public ResponseEntity<?> softDeleteUserById(@PathVariable Long userId) {
		System.out.println("in soft delete" + userId);
		return ResponseEntity.ok(userService.softDeleteUserById(userId));
	}

	// activate the user with email and password
	@PatchMapping("/activate")
	@Operation(description = "Soft delete a user with userId")
	public ResponseEntity<?> activateUser(@RequestBody @Valid SignInRequestDto activationreqDto) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.activateUser(activationreqDto));

	}

}
