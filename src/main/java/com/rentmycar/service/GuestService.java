package com.rentmycar.service;

import java.util.List;
import java.util.Optional;

import com.rentmycar.dto.GetAllUsersDto;
import com.rentmycar.dto.UserDetailsResponseDto;
import com.rentmycar.dto.UpdateBasicUserDetailsDto;

public interface GuestService {

	List<GetAllUsersDto> getAllGuests();

}
