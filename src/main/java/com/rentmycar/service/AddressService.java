package com.rentmycar.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;

import com.rentmycar.dto.AddressDto;
import com.rentmycar.dto.AddressResDto;
import com.rentmycar.dto.ApiResponseDto;
import com.rentmycar.dto.DeleteAddressResDto;

public interface AddressService {

	// add address by userId
	public Optional<AddressResDto> addAddress(AddressDto addressDto, Long userId);

	// get address by address id
	public Optional<AddressDto> getAddressByAddressId(Long id);

	// get address list by user id
	public Optional<List<AddressDto>> getAddressListbyUSerId(Long userId);

	// update address by address id
	public Optional<AddressDto> updateAddressbyAddressId(AddressDto addressDto, Long addressId);

	// soft delete address by address id
	public Optional<DeleteAddressResDto> deleteAddressByAddressId(Long addressId);

	// get distinct cities from car_listing_address
	public Optional<List<String>> getdistinctCityNames();
}
