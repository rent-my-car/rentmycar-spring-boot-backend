package com.rentmycar.service;

import java.util.Optional;

import com.rentmycar.dto.ApiResponseDto;
import com.rentmycar.dto.RegisterUserReqDto;
import com.rentmycar.dto.RegisterUserResDto;
import com.rentmycar.dto.RegisterUserWithDlReqDto;
import com.rentmycar.dto.RegisterUserWithDlResDto;
import com.rentmycar.dto.SignInRequestDto;
import com.rentmycar.dto.SignInResponseDto;
import com.rentmycar.dto.UpdateBasicUserDetailsDto;
import com.rentmycar.dto.UserDetailsResponseDto;

public interface UserService {
	
	Optional<SignInResponseDto> authenticateUser(SignInRequestDto request);
	
	//register user with basic details
	Optional<RegisterUserResDto> registerUser(RegisterUserReqDto registerHostRequestDto);
	
	// Register User with driving license
	Optional<RegisterUserWithDlResDto> registerUser(RegisterUserWithDlReqDto registerUserWithDlReqDto);
	
	//update user basic details
	Optional<UpdateBasicUserDetailsDto> updateBasicUserDetails(Long userId,UpdateBasicUserDetailsDto updatedUserDetails);

	//get basic user details
	Optional<UserDetailsResponseDto> getUserProfileDetails(Long userId);

	//soft-delete user with userId
	ApiResponseDto softDeleteUserById(Long userId);
	
	//activate the user with email and password
	ApiResponseDto activateUser(SignInRequestDto activationreqDto);
}
