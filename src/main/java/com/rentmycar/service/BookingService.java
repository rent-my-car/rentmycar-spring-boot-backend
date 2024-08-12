package com.rentmycar.service;

import java.util.List;
import java.util.Optional;

import com.rentmycar.dto.BookingCardDto;
import com.rentmycar.dto.BookingDto;
import com.rentmycar.dto.BookingResponseDto;

public interface BookingService {

	public Optional<BookingResponseDto> addBooking(BookingDto bookingDto,Long guestId,Long guestAddressId,Long carListingId);
	
	public Optional<List<BookingCardDto>> getPastBookings(Long userId);
}
