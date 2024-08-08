package com.rentmycar.service;

import java.util.Optional;

import com.rentmycar.dto.GuestDetailsResponseDto;

public interface GuestService {

	Optional<GuestDetailsResponseDto> getGuestProfileDetails(Long guestId);
}
