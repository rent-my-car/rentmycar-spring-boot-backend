package com.rentmycar.service;

import java.util.List;
import java.util.Optional;

import com.rentmycar.dto.GetAllUsersDto;
import com.rentmycar.dto.GuestDetailsResponseDto;
import com.rentmycar.dto.UpdateBasicUserDetailsDto;

public interface GuestService {

	Optional<GuestDetailsResponseDto> getGuestProfileDetails(Long guestId);

	List<GetAllUsersDto> getAllGuests();

}
