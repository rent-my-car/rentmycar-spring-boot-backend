package com.rentmycar.service;

import java.util.Optional;
import com.rentmycar.dto.SignInRequestDto;
import com.rentmycar.dto.SignInResponseDto;
import com.rentmycar.dto.UpdateBasicUserDetailsDto;

public interface UserService {
	
	Optional<SignInResponseDto> authenticateUser(SignInRequestDto request);
	
	Optional<UpdateBasicUserDetailsDto> updateBasicUserDetails(Long userId,UpdateBasicUserDetailsDto updatedUserDetails);

}
