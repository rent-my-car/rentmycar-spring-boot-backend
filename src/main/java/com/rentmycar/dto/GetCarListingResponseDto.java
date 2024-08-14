package com.rentmycar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetCarListingResponseDto {

	public GetCarListingResponseDto(CarDetailsDto carDetailsDto, CarPricingDto carPricingDto,
			CarFeaturesDto carFeaturesDto, AddressDto carAddressDto) {
		super();
		this.carDetailsDto = carDetailsDto;
		this.carPricingDto = carPricingDto;
		this.carFeaturesDto = carFeaturesDto;
		this.carAddressDto = carAddressDto;
	}

	CarDetailsDto carDetailsDto;

	CarPricingDto carPricingDto;

	CarFeaturesDto carFeaturesDto;

	AddressDto carAddressDto;

}
