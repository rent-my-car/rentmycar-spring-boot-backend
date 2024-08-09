package com.rentmycar.service;

import java.util.Optional;

import com.rentmycar.dto.AddressDto;
import com.rentmycar.dto.AddressResDto;

public interface AddressService {

	// add address by userId
	public Optional<AddressResDto> addAddress(AddressDto addressDto, Long userId);
	
	// get address by address id
	public Optional<AddressDto> getAddressByAddressId(Long id);

}
