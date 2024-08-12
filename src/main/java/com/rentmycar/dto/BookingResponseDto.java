package com.rentmycar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingResponseDto extends BookingDto {
	private UserDto guest;
	private AddressDto guestAddress;
	private CarListingDto carListing;
}