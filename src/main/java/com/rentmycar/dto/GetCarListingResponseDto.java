package com.rentmycar.dto;

import lombok.Data;

@Data
public class GetCarListingResponseDto {

	CarDetailsDto carDetailsDto;

	CarPricingDto carPricingDto;

	CarFeaturesDto carFeaturesDto;

	AddressDto carAddressDto;

}
