package com.rentmycar.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.rentmycar.dto.AddCarListingDto;
import com.rentmycar.dto.AddCarListingResponseDto;
import com.rentmycar.dto.ApiResponseDto;
import com.rentmycar.dto.CarCardDto;
import com.rentmycar.dto.CityDto;
import com.rentmycar.dto.GetCarListingResponseDto;
import com.rentmycar.dto.UpdateCarListingDto;

public interface CarListingService {

	// add car listing by host id and host address id
	public Optional<AddCarListingResponseDto> addCarListing(Long hostId, Long hostAddressId,
			AddCarListingDto addCarListingDto);

	// get car details by city and date public api
	public Optional<List<CarCardDto>> getCarListing(String city, LocalDateTime pickUp, LocalDateTime dropOff);

	// get car Listing by car_listing_id
	public Optional<GetCarListingResponseDto> getCarListingByCarListingId(Long carListingId);

	// update car_listing by car_listing_id
	public Optional<GetCarListingResponseDto> updateCarListingByCarListingId(Long carListingId,
			UpdateCarListingDto updateCarListingDto);

	// get pending car_listing approvals by host_id
	public Optional<List<CarCardDto>> getPendingApprovalsByHostId(Long hostId);

	// get confirmed car_listings by host_id
	public Optional<List<CarCardDto>> getConfirmedApprovalsByHostId(Long hostId);

	

	
}
