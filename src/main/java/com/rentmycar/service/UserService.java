package com.rentmycar.service;

import java.util.Optional;
import com.rentmycar.dto.SignInRequestDto;
import com.rentmycar.dto.SignInResponseDto;

public interface UserService {
	
	Optional<SignInResponseDto> authenticateUser(SignInRequestDto request);
	

}
