package com.rentmycar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarListingDto {

	private UpdateCarDetailsDto updateCarDetailsDto;

	private CarPricingDto carPricingDto;

	private AddressDto carAddressDto;

}
