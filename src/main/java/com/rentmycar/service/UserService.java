package com.rentmycar.service;

import java.util.Optional;

import com.rentmycar.dto.RegisterUserReqDto;
import com.rentmycar.dto.RegisterUserResDto;
import com.rentmycar.dto.RegisterUserWithDlReqDto;
import com.rentmycar.dto.RegisterUserWithDlResDto;
import com.rentmycar.dto.SignInRequestDto;
import com.rentmycar.dto.SignInResponseDto;

public interface UserService {
	
	Optional<SignInResponseDto> authenticateUser(SignInRequestDto request);
	
	//register user with basic details
	Optional<RegisterUserResDto> registerUser(RegisterUserReqDto registerHostRequestDto);
	
	// Register User with driving license
	Optional<RegisterUserWithDlResDto> registerUser(RegisterUserWithDlReqDto registerUserWithDlReqDto);
	

}
