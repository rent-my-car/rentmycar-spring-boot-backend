package com.rentmycar.service;

import java.util.Optional;

import com.rentmycar.dto.AddCarListingDto;
import com.rentmycar.dto.AddCarListingResponseDto;
import com.rentmycar.dto.GetCarListingResponseDto;

public interface CarListingService {

	// add car listing by host id and host address id
	public Optional<AddCarListingResponseDto> addCarListing(Long hostId, Long hostAddressId,
			AddCarListingDto addCarListingDto);

	// get car Listing by car_listing_id
	public Optional<GetCarListingResponseDto> getCarListingByCarListingId(Long carListingId);

}
