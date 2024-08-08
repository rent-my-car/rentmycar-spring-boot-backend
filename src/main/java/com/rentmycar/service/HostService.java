package com.rentmycar.service;

import java.util.List;

import com.rentmycar.dto.GetAllUsersDto;

public interface HostService {

	// get all hosts
	public List<GetAllUsersDto> getAllHosts();

}
