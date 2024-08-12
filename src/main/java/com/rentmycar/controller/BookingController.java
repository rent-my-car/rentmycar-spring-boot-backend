package com.rentmycar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentmycar.custom_exception.ApiException;
import com.rentmycar.dto.BookingDto;
import com.rentmycar.entity.Booking;
import com.rentmycar.service.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping("/{guestId}/{carId}/{addressId}")
	public ResponseEntity<?> createBooking(@RequestBody BookingDto bookingDto, @PathVariable Long guestId,
			 @PathVariable Long addressId, Long carListingId) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.addBooking(bookingDto,guestId,addressId,carListingId)
				.orElseThrow(() -> new ApiException("internal server error")));
	}

}
