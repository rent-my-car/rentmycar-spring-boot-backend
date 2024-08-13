package com.rentmycar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetCarListingResponseDto {

	CarDetailsDto carDetailsDto;

	CarPricingDto carPricingDto;

	CarFeaturesDto carFeaturesDto;

	AddressDto carAddressDto;

}
