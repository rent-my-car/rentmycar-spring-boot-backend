package com.rentmycar.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.rentmycar.dto.AddCarListingDto;
import com.rentmycar.dto.AddCarListingResponseDto;
import com.rentmycar.dto.CarCardDto;

public interface CarListingService {

	// add car listing by host id and host address id
	public Optional<AddCarListingResponseDto> addCarListing(Long hostId, Long hostAddressId,
			AddCarListingDto addCarListingDto);
	
	// get car Listing by car_listing_id
	
	
	//get car details by city and date public api
	public Optional<List<CarCardDto>> getCarListing(String city,LocalDateTime pickUp,LocalDateTime dropOff);

}
