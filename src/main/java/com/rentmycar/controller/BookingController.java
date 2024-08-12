package com.rentmycar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentmycar.custom_exception.ApiException;
import com.rentmycar.dto.BookingDto;
import com.rentmycar.dto.PaymentRequestDto;
import com.rentmycar.service.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping("/{guestId}/{carId}/{guestAddressId}")
	public ResponseEntity<?> createBooking(@RequestBody BookingDto bookingDto, @PathVariable Long guestId,
			@PathVariable Long guestAddressId, Long carListingId) {

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(bookingService.addBooking(bookingDto, guestId, guestAddressId, carListingId)
						.orElseThrow(() -> new ApiException("Internal server error")));
	}

	// method to get past bookings By userId
	@GetMapping("/past_booking/{userId}")
	public ResponseEntity<?> getPastBookings(@PathVariable Long userId) {
		return ResponseEntity.status(HttpStatus.OK).body(
				bookingService.getPastBookings(userId).orElseThrow(() -> new ApiException("Internal Server Error")));
	}

	// method to get upcoming bookings By userId
	@GetMapping("/upcoming_booking/{userId}")
	public ResponseEntity<?> getUpcomingBookings(@PathVariable Long userId) {
		return ResponseEntity.status(HttpStatus.OK).body(bookingService.getUpcomingBookings(userId)
				.orElseThrow(() -> new ApiException("Internal Server Error")));
	}

	// method to update booking after payment is made
	@PatchMapping("/confirm_booking/{bookingId}")
	public ResponseEntity<?> confirmBooking(@PathVariable Long bookingId,
			@RequestBody PaymentRequestDto paymentRequestDto) {
		return ResponseEntity.status(HttpStatus.OK).body(bookingService.confirmBooking(bookingId, paymentRequestDto)
				.orElseThrow(() -> new ApiException("Internal Server Error")));
	}
}
