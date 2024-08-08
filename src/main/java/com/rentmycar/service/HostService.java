package com.rentmycar.service;

import java.util.List;
import java.util.Optional;

import com.rentmycar.dto.GetAllUsersDto;
import com.rentmycar.dto.RegisterHostRequestDto;
import com.rentmycar.dto.RegisterHostResponseDto;

public interface HostService {
	Optional<RegisterHostResponseDto> registerHost(RegisterHostRequestDto registerHostRequestDto);
	List<GetAllUsersDto>getAllHost();
}
