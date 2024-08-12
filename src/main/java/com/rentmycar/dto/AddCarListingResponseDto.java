package com.rentmycar.dto;

import lombok.Data;

@Data
public class AddCarListingResponseDto extends CarListingDto {

	private UserDto hostDto;

	private AddressDto hostAddressDto;

	private CarPricingDto carPricingDto;

	private CarDto carDto;

	private CarFeaturesDto carFeaturesDto;

}
