package com.rentmycar.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.rentmycar.custom_exception.ApiException;
import com.rentmycar.dto.AddCarListingDto;
import com.rentmycar.dto.ApiResponseDto;
import com.rentmycar.dto.CarCardDto;
import com.rentmycar.dto.UpdateCarListingDto;
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

	// get all cars by city public api
	@GetMapping("/get_cars_by_city")
	public ResponseEntity<?> getCarListingByCity(@RequestParam String city, @RequestParam String pickupDateTime,
			@RequestParam String dropOffDateTime) {
		LocalDateTime pickUp = LocalDateTime.parse(pickupDateTime);
		LocalDateTime dropOff = LocalDateTime.parse(dropOffDateTime);

		// Call the service method to get the car listings
		Optional<List<CarCardDto>> carCardDetailsDtos = carListingService.getCarListing(city, pickUp, dropOff);

		// Check if the list is present and not empty
		if (carCardDetailsDtos.isPresent() && !carCardDetailsDtos.get().isEmpty()) {
			// Return the list of car cards with a status of 201
			return ResponseEntity.status(HttpStatus.OK).body(carCardDetailsDtos.get());
		} else {
			// Return a 400 Bad Request with an appropriate error message
			ApiResponseDto errorResponse = new ApiResponseDto("No cars available for the specified criteria");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}

	// get car Listing by car_listing_id
	@Operation(description = "get car Listing by car_listing_id")
	@GetMapping("/{carListingId}")
	public ResponseEntity<?> getCarListingByCarListingId(@PathVariable Long carListingId) {
		return ResponseEntity.status(HttpStatus.OK).body(carListingService.getCarListingByCarListingId(carListingId)
				.orElseThrow(() -> new ApiException("interanl server error")));
	}

	// update car_listing by car_listing_id
	@Operation(description = "update car_listing by car_listing_id")
	@PatchMapping("/{carListingId}")
	public ResponseEntity<?> updateCarListingByCarListingId(@PathVariable Long carListingId,
			@RequestBody UpdateCarListingDto updateCarListingDto) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(carListingService.updateCarListingByCarListingId(carListingId, updateCarListingDto)
						.orElseThrow(() -> new ApiException("interanl server error")));

	}

}
