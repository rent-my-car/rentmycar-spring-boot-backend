package com.rentmycar.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentmycar.custom_exception.ApiException;
import com.rentmycar.dto.AddCarListingDto;
import com.rentmycar.dto.GetCarListingResponseDto;
import com.rentmycar.service.CarListingService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/car_listing")
public class CarListingController {

	@Autowired
	CarListingService carListingService;

	// add car listing by host id and host address id
	@Operation(description = "add car listing by host id and host address id")
	@PostMapping("/{hostId}/{hostAddressId}")
	public ResponseEntity<?> addCarListing(@PathVariable Long hostId, @PathVariable Long hostAddressId,
			@RequestBody AddCarListingDto addCarListingDto) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(carListingService.addCarListing(hostId, hostAddressId, addCarListingDto)
						.orElseThrow(() -> new ApiException("interanl server error")));
	}

	// get car Listing by car_listing_id
	@Operation(description = "get car Listing by car_listing_id")
	@GetMapping("/{carListingId}")
	public ResponseEntity<?> getCarListingByCarListingId(@PathVariable Long carListingId) {
		return ResponseEntity.status(HttpStatus.OK).body(carListingService.getCarListingByCarListingId(carListingId)
				.orElseThrow(() -> new ApiException("interanl server error")));
	}

}
