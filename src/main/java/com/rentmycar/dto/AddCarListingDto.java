package com.rentmycar.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AddCarListingDto {

	@NotBlank
	private CarDetailsDto carDetailsDto;

	@NotBlank
	private CarPricingDto carPricingDto;

	@NotBlank
	private CarFeaturesDto carFeaturesDto;

}
