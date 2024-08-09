package com.rentmycar.service;

import java.util.Optional;

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

	Optional<UpdateBasicUserDetailsDto> updateBasicUserDetails(Long userId,UpdateBasicUserDetailsDto updatedUserDetails);


	Optional<UserDetailsResponseDto> getUserProfileDetails(Long userId);


}
