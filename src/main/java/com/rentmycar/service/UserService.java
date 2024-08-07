package com.rentmycar.service;

import java.util.Optional;

import com.rentmycar.dto.GuestDetailsResponseDto;
import com.rentmycar.dto.SignInRequestDto;
import com.rentmycar.dto.SignInResponseDto;

public interface UserService {
	
	Optional<SignInResponseDto> authenticateUser(SignInRequestDto request);
	
	Optional<GuestDetailsResponseDto> getGuestProfileDetails(Long guestId);
}
